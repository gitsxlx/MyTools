package com.tool.demo.thread.example.th8;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition对锁进行更精准控制,例子实现按顺序执行
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class Demo1 {

    private int signal;
    Lock lock = new ReentrantLock();
    Condition a = lock.newCondition();
    Condition b = lock.newCondition();
    Condition c = lock.newCondition();

    public void a() {
        lock.lock();
        while (signal != 0) {
            try {
                a.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("a");
        signal++;
        b.signal();
        lock.unlock();
    }

    public void b() {
        lock.lock();
        while (signal != 1) {
            try {
                b.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("b");
        signal++;
        c.signal();
        lock.unlock();
    }

    public void c() {
        lock.lock();
        while (signal != 2) {
            try {
                c.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("c");
        signal = 0;
        a.signal();
        lock.unlock();
    }


    public static void main(String[] args) {

        Demo1 d1 = new Demo1();

        D D = new D(d1);
        E E = new E(d1);
        F F = new F(d1);

        new Thread(D).start();
        new Thread(E).start();
        new Thread(F).start();

    }

}

class D implements Runnable {

    private Demo1 demo1;

    public D(Demo1 demo1) {
        this.demo1 = demo1;
    }

    @Override
    public void run() {
        while (true) {
            demo1.a();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

class E implements Runnable {

    private Demo1 demo1;

    public E(Demo1 demo1) {
        this.demo1 = demo1;
    }

    @Override
    public void run() {
        while (true) {
            demo1.b();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

class F implements Runnable {

    private Demo1 demo1;

    public F(Demo1 demo1) {
        this.demo1 = demo1;
    }

    @Override
    public void run() {
        while (true) {
            demo1.c();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

