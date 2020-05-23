package com.test.demo.http.nio.jdk.example2;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * NioClientHandler
 *
 * @author WangChao
 * @date 2020/05/13
 */
public class NioClientHandler implements Runnable {

    private Selector selector;

    public NioClientHandler(Selector selector) {
        this.selector = selector;
    }

    //在run方法中处理
    @Override
    public void run() {
        try {
            for (; ; ) {
                //第一步：获取到当前的channel
                int readyChannels = selector.select();
                if (readyChannels == 0) {
                    continue;
                }
                //第二步：获取可用channel的集合
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = (SelectionKey) iterator.next();
                    iterator.remove();
                    //第三步：使用readHandler读取数据
                    if (selectionKey.isReadable()) {
                        readHandler(selectionKey, selector);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readHandler(SelectionKey selectionKey,
            Selector selector) throws IOException {
        //第一步：要从 selectionKey 中获取到已经就绪的channel
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        //第二步：创建buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        //第三步：循环读取服务器端响应信息
        String response = "";
        while (socketChannel.read(byteBuffer) > 0) {
            byteBuffer.flip();
            response += Charset.forName("UTF-8").decode(byteBuffer);
        }
        //第三步：将channel再次注册到selector上，监听他的可读事件
        socketChannel.register(selector, SelectionKey.OP_READ);
        //第四步：将服务器端响应信息打印到本地
        if (response.length() > 0) {
            System.out.println("客户端收到数据：" + response);
        }
    }
}
