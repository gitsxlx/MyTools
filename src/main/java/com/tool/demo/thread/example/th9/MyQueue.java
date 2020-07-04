package com.tool.demo.thread.example.th9;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * MyQueue
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class MyQueue<E> {

    private Object[] obj;

    private int addIndex;
    private int removeIndex;
    private int queueSize;

    private Lock lock = new ReentrantLock();
    Condition addCondition = lock.newCondition();
    Condition removeCondition = lock.newCondition();

    public MyQueue(int count) {
        obj = new Object[count];
    }

    public void add(E e) {

        lock.lock();

        while (queueSize == obj.length) {
            try {
                System.out.println(Thread.currentThread().getName() + " 队列已满，不进行添加...");
                addCondition.await();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }

        obj[addIndex] = e;

        if (++addIndex == obj.length) {
            addIndex = 0;
        }

        queueSize++;

        System.out.println(Thread.currentThread().getName() + " 队列长度为 " + queueSize);

        removeCondition.signal();

        lock.unlock();
    }

    public void remove() {

        lock.lock();

        while (queueSize == 0) {
            try {
                System.out.println(Thread.currentThread().getName() + " 队列为空，不进行移除...");
                removeCondition.await();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }

        obj[removeIndex] = null;

        if (++removeIndex == obj.length) {
            removeIndex = 0;
        }

        queueSize--;

        System.out.println(Thread.currentThread().getName() + " 队列长度为 " + queueSize);

        addCondition.signal();

        lock.unlock();
    }

    public static void main(String[] args) {

        MyQueue<Integer> q = new MyQueue<>(10);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    q.add(1);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    q.add(1);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    q.remove();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }

}
