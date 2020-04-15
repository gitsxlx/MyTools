package com.test.demo.thread.example.t6;

/**
 * Demo3
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class Demo3 {

    public synchronized void a() {
        System.out.println("a");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public synchronized void b() {
        System.out.println("b");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Demo3 d1 = new Demo3();
        Demo3 d2 = new Demo3();

        new Thread(new Runnable() {
            @Override
            public void run() {
                d1.a();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                d2.b();
            }
        }).start();

    }

}
