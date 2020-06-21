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
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 线程复用原理：ThreadPoolExecutor中内部类Woker中runWorker方法是个while循环
 * 注意：线程池不允许使用Executors去创建，而是通过ThreadPoolExecutor的方式手动擦创建
 *
 * 1、好处
 * 		降低资源消耗，提高响应速度，提高线程的可管理性。
 *
 * 	2、线程池创建方式
 * 		1、Executors（尽量不使用这种方式）
 * 		2、ThreadPoolTaskExecutor（封装的ThreadPoolExecutor）
 * 		3、ThreadPoolExecutor
 *
 * 	3、Executors分类
 * 		ExecutorService pool = Executors.newFixedThreadPool(10);  //定长线程池，可控制线程最大并发数，超出的线程会在队列中等待
 * 		ExecutorService pool = Executors.newCachedThreadPool();  //可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程
 * 		ExecutorService pool = Executors.newSingleThreadExecutor();  //单个线程挂掉会重启一个线程一直执行
 * 		ScheduledExecutorService pool = Executors.newScheduledThreadPool(10);  //计划任务
 * 		ExecutorService pool = Executors.newWorkStealingPool();  //ForkJoinPool，1.8新加的
 *
 * 	4、ThreadPoolTaskExecutor使用
 * 		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
 * 		executor.setCorePoolSize(5);//核心线程大小
 * 		executor.setMaxPoolSize(10);//最大线程大小
 * 		executor.setQueueCapacity(100);//队列最大容量
 * 		executor.setKeepAliveSeconds(3000);//存活时间
 * 		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());//拒绝执行时如何处理
 *
 * 		executor.submit(new ThreadDemo());//或者executor.execute(new ThreadDemo());
 *
 * 	5、原生ThreadPoolExecutor方式参数
 * 		corePoolSize 线程池中核心线程数的最大值
 * 		maximumPoolSize 线程池中能拥有最多线程数
 * 		keepAliveTime 表示空闲线程的存活时间
 * 		unit 表示keepAliveTime的单位
 * 		workQueue 用于缓存任务的阻塞队列
 * 		threadFactory 指定创建线程的工厂
 * 		handler 表示当workQueue已满，且池中的线程数达到maximumPoolSize时，线程池拒绝添加新任务时采取的策略。拒绝策略：
 * 			1、AbortPolicy：不处理，直接抛出异常。java默认
 * 			2、CallerRunsPolicy：只用调用者所在线程来运行任务，即提交任务的线程。
 * 			3、DiscardOldestPolicy：LRU策略，丢弃队列里最近最久不使用的一个任务，并执行当前任务。
 * 			4、DiscardPolicy：什么也不做，不处理，丢弃掉，不抛出异常。
 *
 * 		public ThreadPoolExecutor(int corePoolSize,
 * 								  int maximumPoolSize,
 * 								  long keepAliveTime,
 * 								  TimeUnit unit,
 * 								  BlockingQueue<Runnable> workQueue,
 * 								  ThreadFactory threadFactory,
 * 								  RejectedExecutionHandler handler) {}
 *
 * 	6、阻塞队列BlockingQueue接口
 * 		ArrayBlockingQueue：是一个基于数组结构的有界阻塞队列，此队列按FIFO（先进先出）原则对元素进行排序。
 * 		LinkedBlockingQueue：一个基于链表结构的阻塞队列，此队列按FIFO排序元素，吞吐量通常要高于ArrayBlockingQueue，静态工厂方法Executors.newFixedThreadPool()，newSingleThreadExecutor()使用了这个队列。
 * 		SynchronousQueue：一个不存储元素的阻塞队列。每个插入操作必须等到另一个线程调用移除操作，否则插入操作一直处于阻塞状态，吞吐量通常要高于LinkedBlockingQueue，静态工厂方法Executors.newCachedThreadPool使用了这个队列。
 * 		PriorityBlockingQueue：一个具有优先级的无限阻塞队列。
 *
 * 	7、通过实现线程工厂接口自定义线程工厂
 * 		可以定制新线程的名称，所属线程组，优先级，守护状态等信息，一般来说，自定义的线程工厂通常用来设置新线程的名称，向日志中写入信息，维护统计信息等，一个典型的自定义线程工厂应该包含一个定制的Thread子类。
 * 		Executors类提供了默认的线程池工厂defaultThreadFactory() 。
 *
 * 	8、向线程池提交任务
 * 		1、execute(Runnable command)所属顶层接口是Executor；executet方法只能提交Runnable类型的任务，所以无法判断任务是否被线程池执行成功；execute会直接抛出任务执行时的异常
 * 		2、submit(Runnable task)方法既能提交Runnable类型任务也能提交Callable类型任务；submit所属顶层接口是ExecutorService，实现类ThreadPoolExecutor重写了execute方法，抽象类AbstractExecutorService重写了submit方法；submit会吃掉异常，可通过Future的get方法将任务执行时的异常重新抛出
 *
 * 	9、关闭线程池
 * 		1、shutdown()：不会立即终止线程池，而是要等所有任务缓存队列中的任务都执行完后才终止，但再也不会接受新的任务
 * 			只是将线程池的状态设置成SHUTDOWN状态，然后中断所有没有正在执行任务的线程，等待执行任务的线程完成。
 * 		2、shutdownNow()：立即终止线程池，并尝试打断正在执行的任务，并且清空任务缓存队列，返回尚未执行的任务
 * 			shutdownNow首先将线程池的状态设置成STOP，然后尝试停止所有的正在执行或暂停任务的线程，并返回等待执行任务的列表
 *
 * 		它们的原理是遍历线程池中的工作线程（workers），然后逐个调用线程的interrupt()方法来中断线程。
 *
 * 		只要调用了这两个关闭方法中的任意一个，isShutdown方法就会返回true。
 * 		当所有的任务都已关闭后，才表示线程池关闭成功，这时调用isTerminaed方法会返回true。
 *
 * 	10、线程池状态
 * 		1、RUNNING
 * 			(1) 状态说明：线程池处在RUNNING状态时，能够接收新任务，以及对已添加的任务进行处理。
 * 			(2) 状态切换：线程池的初始化状态是RUNNING。线程池被一旦被创建，就处于RUNNING状态，且线程池中的任务数为0
 *
 * 		2、SHUTDOWN
 * 			(1) 状态说明：线程池处在SHUTDOWN状态时，不接收新任务，但能处理已添加的任务。
 * 			(2) 状态切换：调用线程池的shutdown()接口时，线程池由RUNNING -> SHUTDOWN。
 *
 * 		3、STOP
 * 			(1) 状态说明：线程池处在STOP状态时，不接收新任务，不处理已添加的任务，并且会中断正在处理的任务。
 * 			(2) 状态切换：调用线程池的shutdownNow()接口时，线程池由(RUNNING or SHUTDOWN ) -> STOP。
 *
 * 		4、TIDYING
 * 			(1) 状态说明：当所有的任务已终止，ctl记录的”任务数量”为0，线程池会变为TIDYING状态。当线程池变为TIDYING状态时，会执行钩子函数terminated()。terminated()在ThreadPoolExecutor类中是空的，
 * 				若用户想在线程池变为TIDYING时，进行相应的处理；可以通过重载terminated()函数来实现。
 * 			(2) 状态切换：当线程池在SHUTDOWN状态下，阻塞队列为空并且线程池中执行的任务也为空时，就会由 SHUTDOWN -> TIDYING。 当线程池在STOP状态下，线程池中执行的任务为空时，就会由STOP -> TIDYING。
 *
 * 		5、TERMINATED
 * 			(1) 状态说明：线程池彻底终止，就变成TERMINATED状态。
 * 			(2) 状态切换：线程池处在TIDYING状态时，执行完terminated()之后，就会由 TIDYING -> TERMINATED。
 *
 * 	11、动态调整线程池参数
 * 		ThreadPoolExecutor中有setCorePoolSize()，setMaximumPoolSize()，setKeepAliveTime()，setThreadFactory()，setRejectedExecutionHandler()方法可以创建线程池之后，动态调整线程池大小
 *
 * 	12、监控线程池
 * 		ThreadPoolExecutor中
 * 			getTaskCount()：线程池需要执行的任务数量。
 * 			getCompletedTaskCount()：线程池在运行过程中已完成的任务数量，小于或等于taskCount。
 * 			getLargestPoolSize()：线程池里曾经创建过的最大线程数量。通过这个数据可以知道线程池是否曾经满过。如该数值等于线程池的最大大小，则表示线程池曾经满过。
 * 			getPoolSize()：线程池的线程数量。如果线程池不销毁的话，线程池里的线程不会自动销 毁，所以这个大小只增不减。
 * 			getActiveCount()：获取活动的线程数。
 *
 * 			也可以通过扩展线程池进行监控。可以通过继承线程池来自定义线程池，重写线程池的beforeExecute、afterExecute和terminated方法，也可以在任务执行前、执行后和线程池关闭前执行一些代码来进行监控。
 * 			例如，监控任务的平均执行时间、最大执行时间和最小执行时间等，这几个方法在线程池里是空方法
 *
 * 	13、线程池工作过程
 * 		1）当提交一个新任务到线程池时，线程池判断corePoolSize线程池是否都在执行任务，如果有空闲线程，则创建一个新的工作线程来执行任务，直到当前线程数等于corePoolSize；
 * 		2）如果当前线程数为corePoolSize，继续提交的任务被保存到阻塞队列中，等待被执行；
 * 		3）如果阻塞队列满了，那就创建新的线程执行当前任务，直到线程池中的线程数达到maxPoolSize，这时再有任务来，由饱和策略来处理提交的任务，
 * 			如果没有任务，线程池空闲时候，超过keepAliveTime，创建的maximumPoolSize超过corePoolSize数的线程，就会被销毁（从workers中remove）。
 *
 * 		线程池原理：预先启动一些线程，线程无限循环从任务队列中获取一个任务进行执行，直到线程池被关闭。
 * 					如果某个线程因为执行某个任务发生异常而终止，那么重新创建一个新的线程而已。如此反复。
 *
 * 		private final HashSet<Worker> workers = new HashSet<>();
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

    private static int ctlOf(int rs, int wc) { return rs | wc; }
    private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;
    // runState is stored in the high-order bits
    private static final int RUNNING    = -1 << COUNT_BITS;
    private static final int SHUTDOWN   =  0 << COUNT_BITS;
    private static final int STOP       =  1 << COUNT_BITS;
    private static final int TIDYING    =  2 << COUNT_BITS;
    private static final int TERMINATED =  3 << COUNT_BITS;

    public static void main(String[] args) {
        System.out.println(COUNT_BITS + ":" + Integer.toBinaryString(COUNT_BITS));
        System.out.println(CAPACITY + ":" + Integer.toBinaryString(CAPACITY));
        System.out.println(RUNNING + ":" + Integer.toBinaryString(RUNNING));
        System.out.println(SHUTDOWN + ":" + Integer.toBinaryString(SHUTDOWN));
        System.out.println(STOP + ":" + Integer.toBinaryString(STOP));
        System.out.println(TIDYING + ":" + Integer.toBinaryString(TIDYING));
        System.out.println(TERMINATED + ":" + Integer.toBinaryString(TERMINATED));
    }

    public static void main1(String[] args) {

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
    ThreadPoolTaskExecutor poolTask = new ThreadPoolTaskExecutor();

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

        /*((ThreadPoolExecutor) pool).setCorePoolSize(11);
        ((ThreadPoolExecutor) pool).setKeepAliveTime(11, TimeUnit.MILLISECONDS);
        ((ThreadPoolExecutor) pool).setMaximumPoolSize(12);
        ((ThreadPoolExecutor) pool).setThreadFactory(defaultThreadFactory());
        ((ThreadPoolExecutor) pool).setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());

        ((ThreadPoolExecutor) pool).getLargestPoolSize();*/
    }

}
