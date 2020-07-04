package com.tool.demo.thread.example.thr9;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * FutureTask
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class Demo {

    public static void main(String[] args) throws Exception {

        Callable<Integer> call = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("正在计算结果...");
                Thread.sleep(3000);
                return 1;
            }
        };

        FutureTask<Integer> futureTask = new FutureTask<>(call);

        Thread thread = new Thread(futureTask);
        thread.start();

        // do something
        System.out.println("干点别的...");

        Integer result = futureTask.get();

        System.out.println("拿到的结果为 " + result);

    }

}
