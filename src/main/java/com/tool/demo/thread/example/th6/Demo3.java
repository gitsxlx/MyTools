package com.tool.demo.thread.example.th6;

import java.util.concurrent.TimeUnit;

/**
 * sleep不会释放锁
 * wait会释放锁
 * notify会随机叫醒一个处于wait状态的线程
 * notiyAll叫醒所有处于wait状态的线程，争夺到线程片的线程只有一个。等到当前notiyAll锁释放之后才能去获取
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class Demo3 {

    private volatile int signal;

    public synchronized void setSignal() {
        this.signal = 1;
        //notify();
        notifyAll();
        System.out.println("叫醒线程之后休眠开始...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized int getSignal() {
        System.out.println(Thread.currentThread().getName() + " 方法执行了...");
		/*try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}*/
        if (signal != 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " 方法执行完毕...");
        return signal;
    }

    public static void main(String[] args) {

        Demo3 d = new Demo3();
        Target1 t1 = new Target1(d);
        Target2 t2 = new Target2(d);

        new Thread(t2).start();
        new Thread(t2).start();
        new Thread(t2).start();
        new Thread(t2).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(t1).start();

    }

}
