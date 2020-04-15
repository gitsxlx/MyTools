package com.test.demo.reflect;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 自定义类加载器
 * @author Wang.Mr
 * @date 2019年8月11日
 */
public class MyClassLoader extends ClassLoader {

    // javac Message.java
    private static final String MESSAGE_CLASS_PATH = "D:" + File.separator + "Message.class";

    /**
     * 进行指定类加载
     * @parameter className 类的完整的名称"包.类"
     * @return 返回值 一个指定类的class类对象
     * @date 2019年8月11日
     */
    public Class<?> loadData(String className) {
        byte[] data = this.loadClassData(); // 读取二进制数据文件
        if (data != null) {
            return super.defineClass(className, data, 0, data.length);
        }
        return null;
    }

    /**
     * 通过文件进行类的加载
     * @parameter 参数及其意义
     * @return 返回值
     * @date 2019年8月11日
     */
    private byte[] loadClassData() {
        InputStream is = null;
        ByteArrayOutputStream baos = null;  // 将数据加载到内存之中
        try {
            baos = new ByteArrayOutputStream();  // 实例化内存流
            is = new FileInputStream(new File(MESSAGE_CLASS_PATH)); // 文件流加载
            //读取数据
            byte[] bytes = new byte[1024];// 缓冲字节数组，每次都读取1024字节
            int len = -1;//每一次读取到的字节数
            while ((len = is.read(bytes)) != -1) // 如果到达流的末尾，则返回 -1
            {
                baos.write(bytes, 0, len);
                ; //写入文件
            }
            return baos.toByteArray(); // 将所有读取到的字节数据取出
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

}
