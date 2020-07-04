package com.tool.demo.thread.example.thre8;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.http.concurrent.Cancellable;

/**
 * Demo
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class Demo {

    public static void main(String[] args) {

        // 10个线程来处理大量任务
        //ThreadPoolExecutor pool = new ThreadPoolExecutor(10, 10, 0,TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());

        ExecutorService pool = Executors.newFixedThreadPool(10);
        //ExecutorService pool = Executors.newCachedThreadPool();
        //ExecutorService pool = Executors.newSingleThreadExecutor();  //单个线程挂掉会重启一个线程一直执行
        //ScheduledExecutorService pool = Executors.newScheduledThreadPool(10);  //计划任务

        //ExecutorService pool = Executors.newWorkStealingPool();  //ForkJoinPool，1.8新加的

        while (true) {
			/*pool.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName());
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});*/

            //延时5秒执行（计划任务）
			/*pool.schedule(new Runnable() {
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName());
				}
			}, 5, TimeUnit.SECONDS);*/
			
			/*Future<Integer> f = pool.submit(new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					return null;
				}
			});*/

            Future<?> f = pool.submit(new Runnable() {
                @Override
                public void run() {

                }
            });

        }

    }

}
