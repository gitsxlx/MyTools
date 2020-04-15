package com.test.demo.thread.example.threa3;

/**
 * Demo3
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class Demo3 {

    // 1 happens-before 2
    public void a() {
        System.out.println("a"); // 1 启动另外一个线程的线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("b"); // 2
            }
        }).start();
    }

}
