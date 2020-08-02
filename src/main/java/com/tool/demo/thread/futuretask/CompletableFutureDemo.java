package com.tool.demo.thread.futuretask;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * TODO
 *
 * @author WangChao
 * @date 2020/07/27
 **/
public class CompletableFutureDemo {

    public static void main(String[] args) {

        /*CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
                //使用ForkJoinPool线程
                System.out.println("F1  "+Thread.currentThread().getName());
                return "F1";
            }
        );

        //主线程
        System.out.println(Thread.currentThread().getName());
        CompletableFuture<String> future2 = future.whenComplete((s, throwable) -> {
            System.out.println(s);
            System.out.println(throwable);
            //使用主线程
            System.out.println("F2  "+Thread.currentThread().getName());
        });


        future2.join();
        try {
            System.out.println(future2.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }*/

        System.out.println("*********");

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            //使用ForkJoinPool线程
            System.out.println("F1  " + Thread.currentThread().getName());
            return "F1";
        }).thenApply(s -> {
            //使用ForkJoinPool线程
            System.out.println("F2  " + Thread.currentThread().getName());
            return "F2";
        });

        future2.join();
        try {
            System.out.println(future2.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

}
