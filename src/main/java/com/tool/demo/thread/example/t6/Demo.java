package com.tool.demo.thread.example.t6;

/**
 * Demo
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class Demo {

    public synchronized void a() {
        System.out.println("a");
        b();
    }

    public synchronized void b() {
        System.out.println("b");
    }

    public static void main(String[] args) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                Demo d = new Demo();
                d.a();
            }
        }).start();

    }

}
