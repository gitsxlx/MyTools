package com.tool.demo.thread.example.threa3;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Demo2
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class Demo2 {

    private Lock lock = new ReentrantLock();

    //1 happens-before 2

    public void a() {
        lock.lock();
        System.out.println("...");
        lock.unlock(); // 1 解锁
    }

    public void b() {
        lock.lock(); // 2 加锁
        System.out.println("...");
        lock.unlock();
    }

}
