package com.tool.demo.http.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * nio
 *
 * BIO：同步阻塞式IO，服务器实现模式为一个连接一个线程，即客户端有连接请求时服务器端就需要启动一个线程进行处理，如果这个连接不做任何事情会造成不必要的线程开销，当然可以通过线程池机制改善。
 * NIO：同步非阻塞式IO，服务器实现模式为一个请求一个线程，即客户端发送的连接请求都会注册到多路复用器上，多路复用器轮询到连接有I/O请求时才启动一个线程进行处理。
 * AIO(NIO.2)：异步非阻塞式IO，服务器实现模式为一个有效请求一个线程，客户端的I/O请求都是由OS先完成了再通知服务器应用去启动线程进行处理。
 *
 * 传统的BIO里面socket.read()，如果TCP RecvBuffer里没有数据，函数会一直阻塞，直到收到数据，返回读到的数据。
 * 对于NIO，如果TCP RecvBuffer有数据，就把数据从网卡读到内存，并且返回给用户；反之则直接返回0，永远不会阻塞。
 * 最新的AIO(Async I/O)里面会更进一步：不但等待就绪是非阻塞的，就连数据从网卡到内存的过程也是异步的。
 * 换句话说，BIO里用户最关心“我要读”，NIO里用户最关心"我可以读了"，在AIO模型里用户更需要关注的是“读完了”。
 * NIO一个重要的特点是：socket主要的读、写、注册和接收函数，在等待就绪阶段都是非阻塞的，真正的I/O操作是同步阻塞的（消耗CPU但性能非常高）。
 *
 * @author Mr.Wang
 * @date 2019年8月14日
 */
public class NioTest {

    /**
     * FileChannel FileInputStream 读取文件
     * @parameter 参数及其意义
     * @return 返回值
     * @date 2019年8月14日
     */
    public static StringBuilder readFile(String dir) throws Exception {
        FileInputStream input = new FileInputStream(dir);
        //获取通道
        FileChannel fc = input.getChannel();
        //创建缓冲区.
        ByteBuffer buffer = ByteBuffer.allocate(2048);
        int n = -1;
        StringBuilder sb = new StringBuilder("");
        while ((n = fc.read(buffer)) != -1) {
            //System.out.println(buffer);
            System.out.println(n);
            buffer.flip();
            /*flip,将limit设置为当前的position的位置,position=0*/
            //sb.append(new String(buffer.array(),0,n));
            byte[] b = new byte[n];
            buffer.get(b);
            sb.append(new String(b));
            System.out.println(buffer);
            /*清空缓冲区让缓冲区可以重新进入读取数据,设置当前的limit为capacity,position为0*/
            buffer.clear();
        }
        if (null != input) {
            input.close();
        }
        return sb;
    }

    /**
     * FileChannel RandomAccessFile 读取文件
     * @parameter 参数及其意义
     * @return 返回值
     * @date 2019年8月14日
     */
    public static void readFile() throws Exception {
        RandomAccessFile aFile = new RandomAccessFile("d:\\text\\group.txt", "rw");
        FileChannel inChannel = aFile.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(2048);
        int bytesRead = inChannel.read(buf);
        while (bytesRead != -1) {
            System.out.print(bytesRead);
            buf.flip();
            while (buf.hasRemaining()) {
                /*利用get进行直接的读取,也可以get一个byte数组*/
                System.out.print((char) buf.get());
            }
            buf.clear();
            bytesRead = inChannel.read(buf);
        }
        aFile.close();
    }

    /**
     * FileChannel FileOutputStream 写文件
     * @parameter 参数及其意义
     * @return 返回值
     * @date 2019年8月14日
     */
    public static boolean writeFile(String dir, String content) throws Exception {
        FileOutputStream output = new FileOutputStream(dir);
        FileChannel fout = output.getChannel();
        byte[] contenByte = content.getBytes("GBK");
        //		byte[] contenByte = content.getBytes();

        //方法1:
        //ByteBuffer buffer = ByteBuffer.allocate(contenByte.length);
        //buffer.put(contenByte);
        //buffer.flip();
        //方法二:
        ByteBuffer buffer = ByteBuffer.wrap(contenByte);
        fout.write(buffer);
        fout.close();
        output.close();
        return true;
    }

    /**
     * 文件直接映射到内存中进行读取
     * @parameter 参数及其意义
     * @return 返回值
     * @date 2019年8月14日
     */
    public static StringBuilder FileMap(String dir) throws Exception {
        /*打开文件刘*/
        FileInputStream input = new FileInputStream(dir);
        /*获取管道*/
        FileChannel fc = input.getChannel();
        MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_ONLY,
                0, fc.size());
        StringBuilder sb = new StringBuilder("");
        byte[] b = new byte[(int) fc.size()];
        mbb.get(b);
        sb.append(new String(b));
        input.close();
        return sb;
    }

    public static void main(String[] args) throws Exception {
        StringBuilder sb = null;
        //sb = FileMap("d:\\text\\group.txt");
        readFile();
        // sb = readFile("d:\\text\\group.txt");
        //writeFile("d:\\text\\group_cpy.txt",sb.toString());
        System.out.println("");

        System.out.println(sb);
    }

}
