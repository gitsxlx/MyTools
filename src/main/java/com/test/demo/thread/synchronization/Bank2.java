package com.test.demo.thread.synchronization;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock
 *
 * @author WangChao
 * @date 2020/04/13
 */
public class Bank2 {

    private int count = 0; // 金额
    // 显式锁
    private Lock lock = new ReentrantLock();

    // 加钱
    public void addMoney(int money) {
        lock.lock(); // 加锁
        try {
            count += money;
            System.out.println(System.currentTimeMillis() + ":" + money);

        } finally {
            lock.unlock(); // 释放锁
        }
    }

    // 减钱
    public void subMoney(int money) {
        lock.lock();
        try {

            if (count - money < 0) {
                System.out.println("金额不够");
                return;
            }
            count -= money;
            System.out.println(+System.currentTimeMillis() + ":" + money);
        } finally {
            lock.unlock();
        }
    }

    // 查看钱数
    public void lookMoney() {
        System.out.println("当前金额:" + count);
    }

}
