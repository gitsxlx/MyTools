package com.tool.demo.http.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;

/**
 * AioServerHandle
 *
 * @author WangChao
 * @date 2020/05/14
 */
public class AioServerHandle extends Thread {

    AsynchronousServerSocketChannel serverSocketChannel;

    public AioServerHandle() {
        try {
            serverSocketChannel = AsynchronousServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress("127.0.0.1", 8888));
            System.out.println("服务端初始化成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        serverSocketChannel.accept(this, new AcceptCompleteHandler(serverSocketChannel));
        try {
            Thread.sleep(100000000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
