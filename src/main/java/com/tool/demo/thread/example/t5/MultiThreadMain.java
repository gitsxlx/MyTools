package com.tool.demo.thread.example.t5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * MultiThreadMain
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class MultiThreadMain {

    public static void main(String[] args) {

        ExecutorService threadPool = Executors.newFixedThreadPool(20);

        for (int i = 0; i < 20; i++) {
            threadPool.execute(new Runnable() {

                @Override
                public void run() {
                    System.out.println(
                            Thread.currentThread().getName() + ":" + Singleton2.getSingleton());

                }
            });
        }

        threadPool.shutdown();

    }

}
