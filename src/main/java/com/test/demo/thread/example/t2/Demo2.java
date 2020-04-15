package com.test.demo.thread.example.t2;

/**
 * 作为线程任务存在
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class Demo2 implements Runnable {

    @Override
    public void run() {
        while (true) {
            System.out.println("thread running...");
        }

    }

    public static void main(String[] args) {

        Thread thread = new Thread(new Demo2());
        thread.start();

    }

}
