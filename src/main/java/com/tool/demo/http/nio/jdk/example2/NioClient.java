package com.tool.demo.http.nio.jdk.example2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;

/**
 * 模拟聊天 NioClient
 *
 * @author WangChao
 * @date 2020/05/13
 */
public class NioClient {

    public static void main(String[] args) {
        new NioClient().start("NioClient");
    }

    public void start(String name) {
        try {
            //第一步：连接服务器端
            SocketChannel socketChannel = SocketChannel.open(
                    new InetSocketAddress("127.0.0.1", 8000));
            //第二步：新建selector
            Selector selector = Selector.open();
            //第三步：socketChannel设置为非阻塞
            socketChannel.configureBlocking(false);
            //第四步：注册通道到selector上
            socketChannel.register(selector, SelectionKey.OP_READ);
            //第五步：处理数据
            //第一种情况：处理服务器返回的数据
            new Thread(new NioClientHandler(selector)).start();
            //第二种情况：向服务器发送数据
            new Thread(() -> {
                try {
                    for (; ; ) {
                        String request = LocalDateTime.now() + "大家好，我是" + name;
                        if (request != null && request.length() > 0) {
                            ByteBuffer sendBuffer = ByteBuffer.wrap(request.getBytes("UTF-8"));
                            socketChannel.write(sendBuffer);
                        }
                        Thread.sleep(500);
                    }
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
            System.out.println("客户器启动成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
