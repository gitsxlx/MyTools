package com.tool.demo.jvm.jv2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * DemoThread
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class ThreadInitializeTest {

    static class Hello {

        static {
            System.out.println(Thread.currentThread().getName() + " init...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        ExecutorService threadPool = Executors.newFixedThreadPool(20);

        int i = 0;
        while (i++ < 20) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " start...");
                    Hello h = new Hello();
                    System.out.println(Thread.currentThread().getName() + " end...");
                }
            });
        }

    }

}
