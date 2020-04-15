package com.test.demo.thread.example.t7;

/**
 * Demo2
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class Demo2 {

    public volatile boolean run = false;

    public static void main(String[] args) {
        Demo2 d = new Demo2();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    System.out.println(Thread.currentThread().getName() + "执行了第" + i + "次");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                d.run = true;
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!d.run) {
                    // 不执行
                }
                System.out.println(Thread.currentThread().getName() + "执行了...");
            }
        }).start();

    }

}
