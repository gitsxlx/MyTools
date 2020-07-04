package com.tool.demo.http.aio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * AcceptCompleteHandler
 *
 * @author WangChao
 * @date 2020/05/14
 */
public class AcceptCompleteHandler implements
        CompletionHandler<AsynchronousSocketChannel, AioServerHandle> {

    //第一部分
    private AsynchronousServerSocketChannel serverSocketChannel;

    public AcceptCompleteHandler(AsynchronousServerSocketChannel serverSocketChannel) {
        this.serverSocketChannel = serverSocketChannel;
    }

    //第二部分
    @Override
    public void completed(final AsynchronousSocketChannel channel, AioServerHandle attachment) {
        //第二部分第一小节
        attachment.serverSocketChannel.accept(attachment, this);
        System.out.println("有客户端连接进来");
        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
        //第二部分第二小节
        channel.read(readBuffer, readBuffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                attachment.flip();
                byte[] bytes = new byte[attachment.remaining()];
                attachment.get(bytes);
                System.out.println("客户端发送来的数据是：" + new String(bytes));

                String sendMsg = "服务端返回的数据：java";
                ByteBuffer writeBuffer = ByteBuffer.allocate(sendMsg.getBytes().length);
                writeBuffer.put(sendMsg.getBytes());
                writeBuffer.flip();
                channel.write(writeBuffer, writeBuffer,
                        new CompletionHandler<Integer, ByteBuffer>() {
                            @Override
                            public void completed(Integer result, ByteBuffer attachment) {
                                if (attachment.hasRemaining()) {
                                    channel.write(attachment, attachment, this);
                                }
                            }

                            @Override
                            public void failed(Throwable exc, ByteBuffer attachment) {
                                try {
                                    System.out.println("服务端出现写数据异常");
                                    channel.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
            }

            //第二部分第三小节
            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                try {
                    System.out.println("服务端读取数据异常");
                    serverSocketChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //第三部分
    @Override
    public void failed(Throwable exc, AioServerHandle attachment) {
        System.out.println("服务端连接异常");
    }
}
