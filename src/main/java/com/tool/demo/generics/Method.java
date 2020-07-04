package com.tool.demo.generics;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * 非泛型类中定义泛型方法
 *
 * @author Administrator
 *
 */
public class Method {

    // 泛型方法，在返回类型前面使用泛型字母
    public static <T> void test1(T t) {
        System.out.println(t);
    }

    // T 只能是list 或者list 的子类
    public static <T extends List> void test2(T t) {
        t.add("aa");
    }

    // T... 可变参数 ---> T[]
    public static <T extends Closeable> void test3(T... a) {
        for (T temp : a) {
            try {
                if (null != temp) {
                    temp.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        test1("java 是门好语言");
        test3(new FileInputStream("a.txt"));
    }
}
