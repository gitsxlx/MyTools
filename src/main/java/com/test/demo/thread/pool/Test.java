package com.test.demo.thread.pool;

import static java.util.concurrent.Executors.defaultThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

/**
 * 线程复用原理：ThreadPoolExecutor中内部类Woker中runWorker方法是个while循环
 * 注意：线程池不允许使用Executors去创建，而是通过ThreadPoolExecutor的方式手动擦创建
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class Test {

    private static ExecutorService cachedThreadPool;  //可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程
    private static ExecutorService fixedThreadPool;  //定长线程池，可控制线程最大并发数，超出的线程会在队列中等待
    private static ScheduledExecutorService scheduledThreadPool;  //定长线程池，支持定时及周期性任务执行
    private static ExecutorService singleThreadExecutor;  //单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行
    private static ExecutorService workStealingPool;  //Fork/Join

    public static void main(String[] args) {

        try {
            cachedThreadPool = Executors.newCachedThreadPool();
            System.out.println(
                    "****************************cachedThreadPool*******************************");
            for (int i = 0; i < 4; i++) {
                final int index = i;
                cachedThreadPool.execute(new ThreadForPools(index));
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            cachedThreadPool.shutdown();
        }

        fixedThreadPool = Executors.newFixedThreadPool(2);
        System.out.println(
                "****************************fixedThreadPool*******************************");
        for (int i = 0; i < 4; i++) {
            final int index = i;
            fixedThreadPool.execute(new ThreadForPools(index));
        }

        scheduledThreadPool = Executors.newScheduledThreadPool(2);
        System.out.println(
                "****************************scheduledThreadPool*******************************");
        for (int i = 0; i < 4; i++) {
            final int index = i;
            //延迟三秒执行
            scheduledThreadPool.schedule(new ThreadForPools(index), 3, TimeUnit.SECONDS);
        }

        singleThreadExecutor = Executors.newSingleThreadExecutor();
        System.out.println(
                "****************************singleThreadExecutor*******************************");
        for (int i = 0; i < 4; i++) {
            final int index = i;
            singleThreadExecutor.execute(new ThreadForPools(index));
        }

        workStealingPool = Executors.newWorkStealingPool();

    }

    //推荐使用下边这几种方式创建线程池
    public static ExecutorService newFixedThreadPool(int nThreads) {
        return new ThreadPoolExecutor(nThreads, nThreads,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
    }

    public static ExecutorService newCachedThreadPool() {
        return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
    }

	/*public static ExecutorService newSingleThreadExecutor() {
        return new FinalizableDelegatedExecutorService
            (new ThreadPoolExecutor(1, 1,
                                    0L, TimeUnit.MILLISECONDS,
                                    new LinkedBlockingQueue<Runnable>()));
    }*/

    public static ScheduledExecutorService newScheduledThreadPool(int corePoolSize) {
        return new ScheduledThreadPoolExecutor(corePoolSize);
    }

    //spring线程池
    //ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();

    //线程池不允许使用Executors去创建，而是通过ThreadPoolExecutor的方式，这样的处理方式让写的同学更加明确线程池的运行规则，规避资源耗尽的风险
    //Positive example 1：
    //org.apache.commons.lang3.concurrent.BasicThreadFactory
    ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
            new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true)
                    .build());

    //Positive example 2：
    /*ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
            .setNameFormat("demo-pool-%d").build();*/
    ThreadFactory namedThreadFactory = defaultThreadFactory();

    //Common Thread Pool
    ExecutorService pool = new ThreadPoolExecutor(5, 200,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory,
            new ThreadPoolExecutor.AbortPolicy());

    public void myTest() {
        pool.execute(() -> System.out.println(Thread.currentThread().getName()));
        pool.shutdown();//gracefully shutdown
    }

}
