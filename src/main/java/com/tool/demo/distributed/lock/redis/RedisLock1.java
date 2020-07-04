package com.tool.demo.distributed.lock.redis;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.simpleframework.xml.core.Commit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * StringRedisTemplate分布式锁
 * 原理：Boolean setIfAbsent(K key, V value, long timeout, TimeUnit unit);
 * set(lockKey,value,"NX/XX","EX/PX",expireTime)
 *
 * 说明：代码仅供参考，不一定完全适用于生成环境
 *
 * @author WangChao
 * @date 2020/04/24
 */
@Commit
@Slf4j
@Component
public class RedisLock1 {

    private static final Long EXPIRE_TIME = 20000L;

    @Autowired
    private StringRedisTemplate redisTemplate;

    public boolean lock(String key) {
        while (true) {
            try {
                if (doLock(key)) {
                    break;
                }
                Thread.sleep(500);
                log.info("未获得锁，休眠500ms");
            } catch (InterruptedException e) {
                log.error("加锁异常", e);
                return true;
            }
        }
        log.info("获得redis锁，key:{}", key);
        return true;
    }

    /**
     * redis加锁
     * @param key 锁的键值
     * @return
     */
    public boolean doLock(String key) {
        //如果不存在就设置锁的对象及超时时间（如果键不存在则新增,存在则不改变已经有的值。）
        if (redisTemplate.opsForValue()
                .setIfAbsent(key, key, EXPIRE_TIME, TimeUnit.MILLISECONDS)) {
            return true;
        }
        return false;
    }

    /**
     * 解锁
     * @param key
     */
    public void unlock(String key) {
        try {
            String currentValue = redisTemplate.opsForValue().get(key);
            if (!StringUtils.isEmpty(currentValue) && currentValue.equals(key)) {
                redisTemplate.opsForValue().getOperations().delete(key);
            }
            log.info("redis解锁：{}", key);
        } catch (Exception e) {
            log.error("解锁异常，{}", e);
        }
    }

    public StringRedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
