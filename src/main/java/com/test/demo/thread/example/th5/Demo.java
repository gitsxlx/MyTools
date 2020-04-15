package com.test.demo.thread.example.th5;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Demo
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class Demo {

    private Map<String, Object> map = new HashMap<>();

    private ReadWriteLock rwl = new ReentrantReadWriteLock();

    private Lock r = rwl.readLock();

    private Lock w = rwl.writeLock();

    private boolean isUpdate;

    public void readWrite() {
        r.lock();  //为了保证isUpdate能拿到最新的值
        if (isUpdate) {
            r.unlock();
            w.lock();
            map.put("xxx", "xxx");
            r.lock();
            w.unlock();
        }

        Object obj = map.get("xxx");

        System.out.println(obj);
        r.unlock();
    }

    public Object get(String key) {
        r.lock();
        System.out.println(Thread.currentThread().getName() + " 读操作在执行...");
        try {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return map.get(key);
        } finally {
            r.unlock();
            System.out.println(Thread.currentThread().getName() + " 读操作执行完毕...");
        }
    }

    public void put(String key, Object value) {
        System.out.println(Thread.currentThread().getName() + " 写操作在执行...");
        w.lock();
        try {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key, value);
        } finally {
            w.unlock();
            System.out.println(Thread.currentThread().getName() + " 写操作执行完毕...");
        }

    }

}
