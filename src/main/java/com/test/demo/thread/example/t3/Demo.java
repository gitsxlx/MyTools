package com.test.demo.thread.example.t3;

/**
 * Demo
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class Demo {

    public static void main(String[] args) {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
				while (true) {
					System.out.println(Thread.currentThread().getName());
				}
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
				while (true) {
					System.out.println(Thread.currentThread().getName());
				}
            }
        });

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        });

        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        });

        t1.setPriority(Thread.MAX_PRIORITY);
        t2.setPriority(Thread.MIN_PRIORITY);

        t1.start();
        t2.start();

    }

}
