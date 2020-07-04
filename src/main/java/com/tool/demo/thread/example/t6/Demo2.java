package com.tool.demo.thread.example.t6;

/**
 * Demo2
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class Demo2 {

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
        Demo2 d = new Demo2();

        new Thread(new Runnable() {
            @Override
            public void run() {
                d.a();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                d.b();
            }
        }).start();

    }

}
