package com.test.demo.http.nio.jdk.example2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Set;

/**
 * 模拟聊天 NioServer
 *
 * 1、channel: 通道
 *     FileChannel 从文件中读写数据。
 *     DatagramChannel 能通过UDP读写网络中的数据。
 *     SocketChannel 能通过TCP读写网络中的数据。
 *     ServerSocketChannel 可以监听新进来的TCP连接，像Web服务器那样。对每一个新进来的连接都会创建一个SocketChannel。
 *
 * @see SelectableChannel 作为nio选择器和通道的关键
 *
 * 2、Buffer: 用于和通道进行交互
 *     写入数据到Buffer: 调用flip()方法
 *     从Buffer中读取数据: 调用clear()方法或者compact()方法
 *
 * 3、Selector: 检测一到多个NIO通道，并能够知晓通道是否为诸如读写事件做好准备的组件。
 * @see SelectorProvider 作为选择器的核心部分
 *
 * 实现步骤
 *     第一步：创建Selector
 *     第二步：创建ServerSocketChannel，绑定监听端口
 *     第三步：将Channel设置为非阻塞模式
 *     第四步：将Channel注册到Selector上，监听连接事件
 *     第五步：循环调用Selector的select方法，检测就绪情况
 *     第六步：调用selectedKeys方法获取就绪channel集合
 *     第七步：判断就绪事件种类，调用业务处理方法
 *     第八步：根据业务需要决定是否再次注册监听事件，重复执行第三步操作
 *
 * NIO还有一个大坑，就是Selector空轮询的时候，导致CPU100%。
 *
 * @author WangChao
 * @date 2020/05/13
 */
public class NioServer {

    public static void main(String[] args) {
        new NioServer().start();
    }

    public void start() {
        try {
            // 第一步：创建Selector
            Selector selector = Selector.open();
            // 第二步：通过ServerSocketChannel创建channel通道
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            // 第三步：为channel通道绑定监听端口
            serverSocketChannel.bind(new InetSocketAddress(8000));
            // 第四步：设置channel为非阻塞模式
            serverSocketChannel.configureBlocking(false);
            // 第五步：将channel注册到selector上，监听连接事件
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("服务器启动成功！");

            for (; ; ) {
                // 获取可用channel数量
                // 阻塞
                int readyChannels = selector.select();
                //int readyChannels = selector.select(1000);
                System.out.println(LocalDateTime.now() + "获取可用channel数量为：" + readyChannels);
                if (readyChannels == 0) {
                    continue;
                }
                // 获取可用channel的集合
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    iterator.remove();
                    // 第七步：根据就绪状态，调用对应方法处理业务逻辑
                    // 第一种情况：如果是连接事件，那就是使用连接Handler处理
                    if (selectionKey.isAcceptable()) {
                        acceptHandler(serverSocketChannel, selector);
                    }
                    //第二种情况：如果是读事件，那就是使用读Handler处理
                    if (selectionKey.isReadable()) {
                        readHandler(selectionKey, selector);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 接收处理
     * @param serverSocketChannel
     * @param selector
     */
    public void acceptHandler(ServerSocketChannel serverSocketChannel,
            Selector selector) {
        try {
            // 第一步：创建socketChannel
            SocketChannel socketChannel = serverSocketChannel.accept();
            // 第二步：将socketChannel设置为非阻塞工作模式
            socketChannel.configureBlocking(false);
            // 第三步：将channel注册到selector上，监听 可读事件
            socketChannel.register(selector, SelectionKey.OP_READ);
            // 第四步：回复客户端提示信息
            String sendString = "您已连接成功";
            ByteBuffer sendBuffer = ByteBuffer.wrap(sendString.getBytes("UTF-8"));
            socketChannel.write(sendBuffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取处理
     * @param selectionKey
     * @param selector
     */
    private void readHandler(SelectionKey selectionKey,
            Selector selector) {
        try {
            // 第一步：要从 selectionKey 中获取到已经就绪的channel
            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
            // 第二步：创建buffer
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            // 第三步：循环读取客户端请求信息
            String result = "";
            while (socketChannel.read(byteBuffer) > 0) {
                // 切换buffer为读模式
                byteBuffer.flip();
                // 读取buffer中的内容
                result += Charset.forName("UTF-8").decode(byteBuffer);
                // 第四步：将channel再次注册到selector上，监听他的可读事件
                socketChannel.register(selector, SelectionKey.OP_READ);

                //第五步：将客户端发送的请求信息 广播给其他客户端
                if (result.length() > 0) {
                    // 广播给其他客户端
                    broadCast(selector, socketChannel, result);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 广播信息
     * @param selector
     * @param sourceChannel
     * @param result
     */
    private void broadCast(Selector selector,
            SocketChannel sourceChannel, String result) {
        //第一步：获取到所有已接入的客户端channel
        Set<SelectionKey> selectionKeySet = selector.keys();
        //第三步：循环向所有channel广播信息
        selectionKeySet.forEach(selectionKey -> {
            Channel targetChannel = selectionKey.channel();
            // 剔除自己：自己不能给自己发信息
            if (targetChannel instanceof SocketChannel
                    && targetChannel != sourceChannel) {
                try {
                    // 将信息发送到targetChannel客户端
                    ByteBuffer sendBuffer = ByteBuffer.wrap(result.getBytes("UTF-8"));
                    ((SocketChannel) targetChannel).write(sendBuffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
