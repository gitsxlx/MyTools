package com.test.demo.distributed.lock.zookeeper.example;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * TODO
 *
 * @author WangChao
 * @date 2020/06/21
 */
public class Main {

    public static void main(String[] args) {
        Thread thread1 = new Thread(new UserThread(), "user1");
        Thread thread2 = new Thread(new UserThread(), "user2");

        thread1.start();
        thread2.start();
    }

    //static Lock lock = new ReentrantLock();
    static Lock lock = new Zklock();

    static class UserThread implements Runnable {

        @Override
        public void run() {
            new Order().createOrder();

            boolean result = false;
            lock.lock();
            try {
                result = new Stock().reduceStock();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

            if (result) {
                System.out.println(Thread.currentThread().getName() + "减库存成功");
                new Pay().pay();
            } else {
                System.out.println(Thread.currentThread().getName() + "减库存失败");
            }
        }
    }

}
