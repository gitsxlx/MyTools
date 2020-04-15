package com.test.demo.http.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * nio
 * @author Mr.Wang
 * @date 2019年8月14日
 */
public class NioTest {

    /**
     * TODO
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
     * TODO
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
     * TODO
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
