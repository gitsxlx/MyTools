package com.tool.demo.distributed.lock.redis;

import java.util.List;
import java.util.UUID;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;
import redis.clients.jedis.exceptions.JedisException;

/**
 * 基于缓存（Redis等）实现分布式锁，Jedis
 *
 * 说明：代码仅供参考，不一定完全适用于生成环境
 *
 * 优点：
 * 1.性能较高
 * 缺点：
 * 1.锁删除失败，过期时间不好控制；
 * 2.非阻塞，操作失败后，需要轮询，占用cpu资源;
 *
 * SET key value 设置指定 key 的值
 * SETEX key seconds value 将值 value 关联到 key ，并将 key 的过期时间设为 seconds (以秒为单位)。
 * SETNX key value 只有在 key 不存在时设置 key 的值
 *
 * GET key
 * DEL key
 * EXISTS key
 * EXPIRE key seconds
 * PEXPIRE key milliseconds
 * TTL key
 *
 * @author WangChao
 * @date 2020/04/24
 */
public class RedisLock {

    /*1、jedis实现：
        ①、获取锁的时候，使用setnx加锁，并使用expire命令为锁添加一个超时时间，超过该时间则自动释放锁，锁的value值为一个随机生成的UUID，通过此在释放锁的时候进行判断。
        ②、获取锁的时候还设置一个获取的超时时间，若超过这个时间则放弃获取锁。
        ③、释放锁的时候，通过UUID判断是不是该锁，若是该锁，则执行delete进行锁释放。*/

    //2、StringRedisTemplate实现
    //在Redis2.6.12版本之前，使用setnx命令设置key-value、使用expire命令设置key的过期时间获取分布式锁，使用del命令释放分布式锁，但这种实现方式会出现死锁、误删持有的锁、主从机制数据不同步的问题。
    //所以，从Redis2.6.12版本开始，通过SET resource_name my_random_value NX PX max-lock-time来实现分布式锁，这个命令仅在不存在key(resource_name)的时候才能被执行成功（NX选项），并且这个key有一个max-lock-time秒的自动失效时间（PX属性）。 Lua 脚本来释放锁。

    //3、使用RedLock算法解决获取锁和释放锁的单点故障问题。

    //4、Redisson实现

    private final JedisPool jedisPool;

    public RedisLock(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    /**
     * 加锁
     * @param lockName       锁的key
     * @param acquireTimeout 获取超时时间
     * @param timeout        锁的超时时间
     * @return 锁标识
     */
    public String lockWithTimeout(String lockName, long acquireTimeout, long timeout) {
        Jedis jedis = null;
        String retIdentifier = null;
        try {
            // 获取连接
            jedis = jedisPool.getResource();
            // 随机生成一个value
            String identifier = UUID.randomUUID().toString();
            // 锁名，即key值
            String lockKey = "lock:" + lockName;
            // 超时时间，上锁后超过此时间则自动释放锁
            int lockExpire = (int) (timeout / 1000);

            // 获取锁的超时时间，超过这个时间则放弃获取锁
            long end = System.currentTimeMillis() + acquireTimeout;
            while (System.currentTimeMillis() < end) {
               /* String result = jedis.set("key", "value", "NX/XX", "EX/PX", 1000);
                if (result != null && result.equalsIgnoreCase("OK")) {
                    flag = true;
                }*/

                if (jedis.setnx(lockKey, identifier) == 1) {
                    jedis.expire(lockKey, lockExpire);
                    // 返回value值，用于释放锁时间确认
                    retIdentifier = identifier;
                    return retIdentifier;
                }
                // 返回-1代表key没有设置超时时间，为key设置一个超时时间
                if (jedis.ttl(lockKey) == -1) {
                    jedis.expire(lockKey, lockExpire);
                }

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        } catch (JedisException e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return retIdentifier;
    }

    /**
     * 释放锁
     * @param lockName   锁的key
     * @param identifier 释放锁的标识
     * @return
     */
    public boolean releaseLock(String lockName, String identifier) {
        Jedis conn = null;
        String lockKey = "lock:" + lockName;
        boolean retFlag = false;
        try {
            conn = jedisPool.getResource();
            while (true) {
                // 监视lock，准备开始事务
                conn.watch(lockKey);
                // 通过前面返回的value值判断是不是该锁，若是该锁，则删除，释放锁
                if (identifier.equals(conn.get(lockKey))) {
                    Transaction transaction = conn.multi();
                    transaction.del(lockKey);
                    List<Object> results = transaction.exec();
                    if (results == null) {
                        continue;
                    }
                    retFlag = true;
                }
                conn.unwatch();
                break;
            }
        } catch (JedisException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return retFlag;
    }

}
