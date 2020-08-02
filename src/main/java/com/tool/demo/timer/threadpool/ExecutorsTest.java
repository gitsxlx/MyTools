package com.tool.demo.timer.threadpool;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

/**
 * 定时任务
 * 4、线程
 * @see ThreadPoolExecutor
 * @see ThreadPoolTaskExecutor
 *
 * @author WangChao
 * @date 2020/04/08
 */
@Slf4j
@Component
public class ExecutorsTest {

    public static final TaskTest me = new TaskTest();

    @PostConstruct
    private void start() {
        me.start();
    }

    //使用线程来控制相对更灵活些，可以根据自己的需要判断什么时候运行，什么时候停止
    public static class TaskTest {
        private static final ExecutorService pool = Executors.newFixedThreadPool(5);// 线程池
        private static final ExecutorService scheduledPool = Executors.newScheduledThreadPool(5);// 线程池
        public static final int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};

        private void start() {
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            for (int i = 0; i < arr.length; i++) {
                                if (1 == arr[i]) {
                                    log.info("Executors start! {}", new Date());
                                    Thread.sleep(1*1000L);
                                }
                                if (9 == arr[i]) {
                                    log.info("Executors end! {}", new Date());
                                    Thread.sleep(5*1000L);
                                }
                                log.info("Executors {} {}",i ,new Date());
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }
}
