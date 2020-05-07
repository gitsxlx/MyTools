package com.test.demo.thread.example.thr4;

/**
 * ThreadLocal
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class Demo {

    private ThreadLocal<Integer> count = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return new Integer(0);
        };
    };

    public int getNext() {
        Integer value = count.get();
        value++;
        count.set(value);
        return value;
    }

    public static void main(String[] args) {

        Demo d = new Demo();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(Thread.currentThread().getName() + " " + d.getNext());
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(Thread.currentThread().getName() + " " + d.getNext());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(Thread.currentThread().getName() + " " + d.getNext());
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }

}
