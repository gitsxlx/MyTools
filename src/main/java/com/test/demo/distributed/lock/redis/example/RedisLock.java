package com.test.demo.distributed.lock.redis.example;

import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.commons.lang3.ThreadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.SetParams;

import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

/**
 * RedisLock
 *
 * @author WangChao
 * @date 2020/06/21
 */
@Component
public class RedisLock implements Lock {

    @Autowired
    private JedisPool jedisPool;

    private static final String key = "lock";

    private ThreadLocal<String> threadLocal = new ThreadLocal<>();

    private static AtomicBoolean isHappened = new AtomicBoolean(true);

    @Override
    public void lock() {
        boolean b = tryLock();  //尝试加锁
        if (b) {
            return;
        }
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock();
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        SetParams setParams = new SetParams();
        setParams.ex(1);
        setParams.nx();
        String s = UUID.randomUUID().toString();
        Jedis resource = jedisPool.getResource();
        String lock = resource.set(key, s, setParams);
        resource.close();
        if ("OK".equals(lock)) {
            threadLocal.set(s);
            if (isHappened.get()) {
                Thread thread = new Thread(new MyRunnable(jedisPool));
                thread.setDaemon(true);
                thread.start();
                isHappened.set(false);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() throws Exception {
        String script = "if redis.call(\"get\",KEYS[1])==ARGV[1] then\n" +
                "    return redis.call(\"del\",KEYS[1])\n" +
                "else\n" +
                "    return 0\n" +
                "end";
        Jedis resource = jedisPool.getResource();
        // resource.del(key);
        Object eval = resource.eval(script, Arrays.asList(key), Arrays.asList(threadLocal.get()));
        if (Integer.valueOf(eval.toString()) == 0) {
            resource.close();
            throw new Exception("解锁失败");
        }
        resource.close();
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    static class MyRunnable implements Runnable {

        private JedisPool jedisPool;

        public MyRunnable(JedisPool jedisPool) {
            this.jedisPool = jedisPool;
        }

        @Override
        public void run() {
            Jedis jedis = jedisPool.getResource();
            while (true) {
                Long ttl = jedis.ttl(key);
                if (ttl != null && ttl > 0) {
                    jedis.expire(key, (int) (ttl + 1));
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
