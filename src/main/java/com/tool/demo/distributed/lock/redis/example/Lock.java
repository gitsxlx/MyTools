package com.tool.demo.distributed.lock.redis.example;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

/**
 * Lock
 *
 * @author WangChao
 * @date 2020/06/21
 */
public interface Lock {

    void lock();

    void lockInterruptibly() throws InterruptedException;

    boolean tryLock();

    boolean tryLock(long time, TimeUnit unit) throws InterruptedException;

    void unlock() throws Exception;

    Condition newCondition();
}
