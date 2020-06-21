package com.test.demo.distributed.lock.redis.example;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import redis.clients.jedis.Jedis;

public class Dome {

    private int count = 100;
//    Lock lock=new ReentrantLock();

    @Autowired
    @Qualifier("redisLock")
    private Lock lock;

    public void testRedis() {
        Jedis jedis = new Jedis("192.168.0.104", 6379);
//        SetParams setParams=new SetParams();
//        setParams.ex(60);
//        setParams.nx();
        String s = UUID.randomUUID().toString();
//        String lock = jedis.set("lock", s,setParams);
        jedis.setnx("lock", s);
        jedis.expire("lock", 60);
        System.out.println(lock);
    }

    public void Test() throws InterruptedException {
        TicketsRunBle ticketsRunBle = new TicketsRunBle();
        Thread thread1 = new Thread(ticketsRunBle, "窗口1");
        Thread thread2 = new Thread(ticketsRunBle, "窗口2");
        Thread thread3 = new Thread(ticketsRunBle, "窗口3");
        Thread thread4 = new Thread(ticketsRunBle, "窗口4");
        Thread thread5 = new Thread(ticketsRunBle, "窗口5");
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        Thread.currentThread().join();
    }

    public class TicketsRunBle implements Runnable {

        @Override
        public void run() {
            while (count > 0) {
                lock.lock();  //10miao
                try {
                    if (count > 0) {
                        System.out.println(Thread.currentThread().getName() + "售出第" + count-- + "张票");
                    } //11
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        lock.unlock();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
