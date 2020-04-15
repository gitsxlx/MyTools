package com.test.demo.thread.example.t9;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Sequence
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class Sequence {

    private int value;
    Lock lock = new ReentrantLock();

    public int getNext() {
        lock.lock();
		try {
        	int a = value++;
        	return a;
		} finally {
			lock.unlock();
		}
    }


    public static void main(String[] args) {

        Sequence s = new Sequence();
		
		/*while(true) {
			System.out.println(s.getNext());
		}*/

        new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    System.out.println(Thread.currentThread().getName() + " " + s.getNext());
                    try {
                        Thread.sleep(100);
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
                    System.out.println(Thread.currentThread().getName() + " " + s.getNext());
                    try {
                        Thread.sleep(100);
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
                    System.out.println(Thread.currentThread().getName() + " " + s.getNext());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }

}
