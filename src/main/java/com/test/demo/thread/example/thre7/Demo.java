package com.test.demo.thread.example.thre7;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Demo
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class Demo {

    public static void main(String[] args) {

//		int corePoolSize,
//      int maximumPoolSize,
//      long keepAliveTime,
//      TimeUnit unit,
//      BlockingQueue<Runnable> workQueue
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(10, 20, 10, TimeUnit.DAYS,
                new ArrayBlockingQueue<>(10), new ThreadPoolExecutor.CallerRunsPolicy());
        AtomicInteger count = new AtomicInteger();
        for (int i = 0; i < 100; i++) {
            poolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                    count.getAndIncrement();
                }
            });
        }

        poolExecutor.shutdown();

        while (Thread.activeCount() > 1) {

        }

        System.out.println(count.get());

    }

}
