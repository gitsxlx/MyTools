package com.tool.demo.thread.example.t6;

/**
 * Demo6
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class Demo6 {

    private Object obj1 = new Object();
    private Object obj2 = new Object();

    public void a() {
        synchronized (obj1) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (obj2) {
                System.out.println("a");
            }
        }
    }

    public void b() {
        synchronized (obj2) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (obj1) {
                System.out.println("b");
            }
        }
    }

    public static void main(String[] args) {

        Demo6 d = new Demo6();

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
