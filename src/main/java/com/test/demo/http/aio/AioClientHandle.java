package com.test.demo.http.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * AioClientHandle
 *
 * @author WangChao
 * @date 2020/05/14
 */
public class AioClientHandle extends Thread implements CompletionHandler<Void, AioClientHandle> {

    private AsynchronousSocketChannel socketChannel;

    public AioClientHandle() {
        try {
            socketChannel = AsynchronousSocketChannel.open();
            System.out.println("客户端初始化成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 8888), this, this);
    }

    @Override
    public void completed(Void result, AioClientHandle attachment) {
        System.out.println("client连接成功");
        String sendMsg = "我是：java，服务端你好";
        ByteBuffer writeBuffer = ByteBuffer.allocate(sendMsg.getBytes().length);
        writeBuffer.put(sendMsg.getBytes());
        writeBuffer.flip();
        socketChannel.write(writeBuffer, writeBuffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                if (attachment.hasRemaining()) {
                    socketChannel.write(writeBuffer, attachment, this);
                } else {
                    ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                    socketChannel.read(readBuffer, readBuffer,
                            new CompletionHandler<Integer, ByteBuffer>() {
                                @Override
                                public void completed(Integer result, ByteBuffer attachment) {
                                    readBuffer.flip();
                                    byte[] bytes = new byte[readBuffer.remaining()];
                                    readBuffer.get(bytes);
                                    System.out.println("客户端读取数据：" + new String(bytes));
                                }

                                @Override
                                public void failed(Throwable exc, ByteBuffer attachment) {
                                    try {
                                        System.out.println("客户端读取数据失败");
                                        socketChannel.close();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                }
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                try {
                    System.out.println("客户端写数据失败");
                    socketChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void failed(Throwable exc, AioClientHandle attachment) {
        try {
            System.out.println("客户端出现异常");
            socketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
