package com.test.demo.thread.example.thr6;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class Demo {

    Random random = new Random();

    public void meeting(CyclicBarrier barrier) {
        try {
            Thread.sleep(random.nextInt(4000));
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + " 到达会议室，等待开会...");

        //一个出现异常，没有执行await，所有线程都会等待
		/*if (Thread.currentThread().getName().equals("Thread-1")) {
			throw new RuntimeException();
		}*/

        try {
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + " 发言");
    }

    public static void main(String[] args) {
        Demo d = new Demo();

        CyclicBarrier barrier = new CyclicBarrier(10, new Runnable() {
            @Override
            public void run() {
                System.out.println("开始开会...");
            }
        });

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    d.meeting(barrier);
                }
            }).start();
        }

    }

}
