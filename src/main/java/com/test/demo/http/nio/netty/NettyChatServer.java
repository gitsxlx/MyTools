package com.test.demo.http.nio.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * NettyServer
 *
 * Netty 对 JDK 自带的 NIO 的 API 进行了封装，解决了原生 NIO 程序的问题
 *
 * Netty 作为异步事件驱动的网络，高性能之处主要来自于其 I/O 模型和线程处理模型，前者决定如何收发数据，后者决定如何处理数据。
 * 1、I/O 模型：
 *   IO 线程 NioEventLoop 由于聚合了多路复用器 Selector，一个 I/O 线程可以并发处理 N 个客户端连接和读写操作，这从根本上解决了传统同步阻塞 I/O 一连接一线程模型。
 *   在 NIO 中，抛弃了传统的 I/O 流，而是引入了 Channel 和 Buffer 的概念。基于 Buffer 操作不像传统 IO 的顺序操作，NIO 中可以随意地读取任意位置的数据。
 * 2、线程模型：
 *   我们设计一个事件处理模型的程序有两种思路：
 *      1）轮询方式：线程不断轮询访问相关事件发生源有没有发生事件，有发生事件就调用事件处理逻辑；
 *      2）事件驱动方式：发生事件，主线程把事件放入事件队列，在另外线程不断循环消费事件列表中的事件，调用事件对应的处理逻辑处理事件。事件驱动方式也被称为消息通知方式，其实是设计模式中观察者模式的思路。
 *   Reactor 线程模型：Reactor 是反应堆的意思，Reactor 模型是指通过一个或多个输入同时传递给服务处理器的服务请求的事件驱动处理模式。
 *   Reactor 模型中有 2 个关键组成：
 *      1）Reactor：Reactor 在一个单独的线程中运行，负责监听和分发事件，分发给适当的处理程序来对 IO 事件做出反应。它就像公司的电话接线员，它接听来自客户的电话并将线路转移到适当的联系人；
 *      2）Handlers：处理程序执行 I/O 事件要完成的实际事件，类似于客户想要与之交谈的公司中的实际官员。Reactor 通过调度适当的处理程序来响应 I/O 事件，处理程序执行非阻塞操作。
 *   取决于 Reactor 的数量和 Handler 线程数量的不同，Reactor 模型有 3 个变种：
 *      1）单 Reactor 单线程；
 *      2）单 Reactor 多线程；
 *      3）主从 Reactor 多线程。
 *   可以这样理解，Reactor 就是一个执行 while (true) { selector.select(); …} 循环的线程，会源源不断的产生新的事件，称作反应堆很贴切。
 *   Netty 主要基于主从 Reactors 多线程模型做了一定的修改，其中主从 Reactor 多线程模型有多个 Reactor：
 *      1）MainReactor 负责客户端的连接请求，并将请求转交给 SubReactor；
 *      2）SubReactor 负责相应通道的 IO 读写请求；
 *      3）非 IO 请求（具体逻辑处理）的任务则会直接写入队列，等待 worker threads 进行处理。
 *    特别说明的是：虽然 Netty 的线程模型基于主从 Reactor 多线程，借用了 MainReactor 和 SubReactor 的结构。但是实际实现上 SubReactor 和 Worker 线程在同一个线程池中。
 *
 * Netty框架的工作原理：
 *  1）初始化创建 2 个 NioEventLoopGroup：其中 boosGroup 用于 Accetpt 连接建立事件并分发请求，workerGroup 用于处理 I/O 读写事件和业务逻辑。
 *  2）基于 ServerBootstrap(服务端启动引导类)：配置 EventLoopGroup、Channel 类型，连接参数、配置入站、出站事件 handler。
 *  3）绑定端口：开始工作。
 *
 *  Netty为什么传输快？
 *  使用了NIO特性——零拷贝，当他需要接收数据的时候，他会在堆内存之外开辟一块内存，数据就直接从IO读到了那块内存中去，在netty里面通过ByteBuf可以直接对这些数据进行直接操作，从而加快了传输速度。
 *
 * @author WangChao
 * @date 2020/05/05
 */
@Component
public class NettyChatServer {

    private int port = 12345;

    EventLoopGroup bossGroup = new NioEventLoopGroup();
    EventLoopGroup group = new NioEventLoopGroup();
    ChannelFuture cf = null;

    @PostConstruct
    public void start() {
        try {
            ServerBootstrap sb = new ServerBootstrap();
            sb.option(ChannelOption.SO_BACKLOG, 1024);
            // 绑定线程池
            sb.group(group, bossGroup)
                    // 指定使用的channel
                    .channel(NioServerSocketChannel.class)
                    // 绑定监听端口
                    .localAddress(this.port)
                    // 绑定客户端连接时候触发操作
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            System.out.println("收到新连接:" + ch.localAddress());
                            // 使用http解编码器
                            ch.pipeline().addLast(new HttpServerCodec());
                            // 以块的方式来写的处理器
                            ch.pipeline().addLast(new ChunkedWriteHandler());
                            ch.pipeline().addLast(new HttpObjectAggregator(8192));
                            ch.pipeline().addLast(new NettyChatServerHandler());
                            ch.pipeline().addLast(
                                    new WebSocketServerProtocolHandler("/ws", "WebSocket", true,
                                            65536 * 10));
                        }
                    });
            // 服务器异步创建绑定
            cf = sb.bind(port).sync();
            // 关闭服务器通道，阻塞
            //cf.channel().closeFuture().sync();
            System.out.println("netty start 启动正在监听： " + cf.channel().localAddress());
        } catch (Exception e) {
            e.printStackTrace();
            // 释放线程池资源
            //group.shutdownGracefully().sync();
            //bossGroup.shutdownGracefully().sync();
        }
    }

    @PreDestroy
    public void end() throws Exception {
        try {
            // 关闭服务器通道，阻塞
            cf.channel().closeFuture().sync();
        } finally {
            // 释放线程池资源
            group.shutdownGracefully().sync();
            bossGroup.shutdownGracefully().sync();
        }
    }
}
