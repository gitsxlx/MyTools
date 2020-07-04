package com.tool.demo.thread.example.t8;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Sequence
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class Sequence {

    //原子更新基本类型
    private AtomicInteger value = new AtomicInteger(0);

    //原子更新数组
    private int[] a = new int[]{1, 2, 3, 4};
    private AtomicIntegerArray array = new AtomicIntegerArray(a);

    //原子更新抽象类型。对user对象操作，不是对user字段
    AtomicReference<User> user = new AtomicReference<>();

    //原子更新字段
    private AtomicIntegerFieldUpdater<User> age = AtomicIntegerFieldUpdater
            .newUpdater(User.class, "age");

    public int getNext() {
        User u = new User();
        age.getAndIncrement(u);
        System.out.println(age.getAndIncrement(u));
        System.out.println(age.getAndIncrement(u));
        System.out.println(age.getAndIncrement(u));

        array.getAndIncrement(2);
        array.getAndAdd(3, 10);
        System.out.println(array);

        return value.getAndIncrement();
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
