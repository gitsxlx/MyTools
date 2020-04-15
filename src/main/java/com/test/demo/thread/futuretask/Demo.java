package com.test.demo.thread.futuretask;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * ThreadForPools
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class Demo implements Callable<String> {

    private static int total = 20;

    @Override
    public String call() throws Exception {
        Thread.sleep(1000);
        while (total > 0) {
            System.out.println(Thread.currentThread().getName() + ": " + total);
            total--;
        }
        return "no tatal";
    }

    public static void main(String[] args) {
        FutureTask<String> thread = new FutureTask<>(new Demo());
        FutureTask<String> thread1 = new FutureTask<>(new Demo());
        new Thread(thread, "1").start();
        new Thread(thread, "2").start();
        new Thread(thread, "one").start();
        new Thread(thread1, "two").start();
        try {
            System.out.println(thread.get());
            System.out.println(thread1.get());
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

