package com.test.demo.thread.example.threa2;

/**
 * Demo
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class Demo {

    private int a, b, c;

    public void a() {

        // 写后读
        // 读后写
        // 写后写

        a = 1; // 写操作
        b = 2;

        c = a; // 读操作
        b = c + a;

        System.out.println(b);
    }

    public static void main(String[] args) {
        new Demo().a();
    }

}
