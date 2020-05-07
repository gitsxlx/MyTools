package com.test.demo.http.nio.multiplex;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * SelectorServer
 *
 * 底层poll方法，调用native方法poll0（WindowsSelectorImpl）
 * epoll（Linux）
 *
 * 基本的IO编程过程（包括网络IO和文件IO）是，打开文件描述符（windows是handler，java是stream或channel），多路捕获（Multiplex，即select和poll和epoll）IO可读写的状态，而后可以读写的文件描述符进行IO读写
 *
 * @author WangChao
 * @date 2020/05/05
 */
public class SelectorServer {

    public static void main(String[] args) {
        try {
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.bind(new InetSocketAddress("127.0.0.1",8000));
            ssc.configureBlocking(false);

            Selector selector = Selector.open();
            // 注册channel，并且指定感兴趣的事件是accept
            ssc.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println(selector.keys().size());
            ByteBuffer readBuff = ByteBuffer.allocate(1024);
            ByteBuffer writeBuff = ByteBuffer.allocate(128);
            writeBuff.put("received".getBytes());
            writeBuff.flip();

            while (true) {
                // 查看select源码
                int nReady = selector.select();
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> it = keys.iterator();

                while (it.hasNext()) {
                    SelectionKey key = it.next();
                    it.remove();

                    if(key.isAcceptable()){
                        // 创建新的连接，并且把连接注册到selector上，而且声明这个channel只对读操作感兴趣
                        SocketChannel socketChannel = ssc.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);
                    } else if(key.isReadable()){
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        readBuff.clear();
                        socketChannel.read(readBuff);

                        readBuff.flip();
                        System.out.println("" + new String(readBuff.array()));
                        key.interestOps(SelectionKey.OP_WRITE);
                    } else if (key.isWritable()){
                        writeBuff.rewind();
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        socketChannel.write(writeBuff);
                        key.interestOps(SelectionKey.OP_READ);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
