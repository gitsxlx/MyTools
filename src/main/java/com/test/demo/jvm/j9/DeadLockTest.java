package com.test.demo.jvm.j9;

/**
 * 死锁示例
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class DeadLockTest {

    public static void main(String[] args) {
        Object obj1 = new Object();
        Object obj2 = new Object();

        new Thread(new DeadLock(obj1, obj2)).start();
        new Thread(new DeadLock(obj2, obj1)).start();
    }

}
