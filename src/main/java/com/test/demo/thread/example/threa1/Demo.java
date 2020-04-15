package com.test.demo.thread.example.threa1;

import java.util.concurrent.locks.StampedLock;

/**
 * StampedLock
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class Demo {

    private int balance;

    private StampedLock lock = new StampedLock();

    // 带条件的更新
    public void conditonReadWrite(int value) {
        // 首先判断balance的值是否符合更新的条件
        long stamp = lock.readLock();
        try {
            while (balance > 0) {
                long writeStamp = lock.tryConvertToWriteLock(stamp);
                if (writeStamp != 0) { // 成功转换为写锁
                    stamp = writeStamp;
                    balance += value;
                    break;
                } else {
                    // 没有转换成写锁，这里需要首先释放读锁，然后再拿到写锁
                    lock.unlockRead(stamp);
                    // 获取写锁
                    stamp = lock.writeLock();
                }
            }
        } finally {
            lock.unlock(stamp);
        }
    }

    //乐观锁
    public void OptimisticRead() {
        long stamp = lock.tryOptimisticRead();
        int c = balance;
        // 这里可能会出现了写操作，因此要进行判断
        if (!lock.validate(stamp)) {
            //要重新读取
            long readStamp = lock.readLock();
            c = balance;
            stamp = readStamp;
        }
        lock.unlockRead(stamp);
    }

    //读锁
    public void read() {
        long stamp = lock.readLock();
        //lock.tryOptimisticRead();
        int c = balance;
        //...
        lock.unlockRead(stamp);
    }

    //写锁
    public void write(int value) {
        long stamp = lock.writeLock();
        try {
            balance += value;
        } finally {
            lock.unlockWrite(stamp);
        }
    }

    public static void main(String[] args) {

    }

}
