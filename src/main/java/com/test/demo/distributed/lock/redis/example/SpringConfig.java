package com.test.demo.distributed.lock.redis.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * SpringConfig
 *
 * @author WangChao
 * @date 2020/06/21
 */
@Configuration
@ComponentScan("com.test.demo.distributed.lock.redis.example")
public class SpringConfig {

//    @Scope("prototype")
//    @Lazy
//    @Bean
//    public Jedis jedis(JedisPool jedisPool){
//        return jedisPool.getResource();
//    }

    @Bean
    public JedisPool jedisPool() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(101);
        jedisPoolConfig.setMaxIdle(1000);
        jedisPoolConfig.setMinIdle(1);
        jedisPoolConfig.setMaxWaitMillis(2000);
        jedisPoolConfig.setTestOnBorrow(true);
        jedisPoolConfig.setTestOnReturn(true);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "192.168.0.104", 6379);
        return jedisPool;
    }
}
