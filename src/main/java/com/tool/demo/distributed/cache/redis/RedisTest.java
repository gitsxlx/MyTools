package com.tool.demo.distributed.cache.redis;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import redis.clients.jedis.Jedis;

/**
 * Redis：REmote DIctionary Server是一个开源的使用ANSI C语言编写、遵守BSD协议、支持网络、可基于内存亦可持久化的日志型、Key-Value数据库。
 *
 * Redis的客户端，Redis的客户端官方推荐有3种：
 * Jedis;
 * Lecttuce;
 * Redisson;
 *
 * Redis Desktop Manager是一款windows平台下的可视化redis数据库桌面管理工具。
 *
 * 单个操作是原子性的。多个操作也支持事务（可以理解为一个打包的批量执行脚本，但批量指令并非原子化的操），通过MULTI和EXEC指令包起来。
 *
 * Redis 脚本使用 Lua 解释器来执行脚本。 Redis 2.6 版本通过内嵌支持 Lua 环境。执行脚本的常用命令为 EVAL。
 *
 * 一个键最大能存储 512MB。
 * GET key
 * DEL key
 * EXISTS key
 * EXPIRE key seconds
 * PEXPIRE key milliseconds
 * TTL key
 *
 * 值（value）可以是
 *  字符串(String): string 类型的值最大能存储 512MB。适合jpg图片或者序列化的对象，在线人数，秒杀。SET和GET命令。
 *  哈希(Hash): 键值对集合，每个 hash 可以存储 2的32次方-1 键值对（40多亿）。适合存储对象。HMSET,HGET命令。
 *  列表(list): 链表(双向链表)，列表最多可存储 2的32次方-1 元素 (4294967295, 每个列表可存储40多亿)。适合消息队列，最新消息排行。lpush,lrange命令。
 *  集合(sets): 集合是通过哈希表实现的,元素不重复，添加，删除，查找的复杂度都是 O(1)。集合中最大的成员数为 2的32次方-1。适合共同好友，统计独立ip。sadd,smembers命令。
 *  有序集合(sorted sets): 将Set中的元素增加一个权重参数score,元素按score有序排列。采用了跳表原理，时间复杂度O(logn)。适合排行榜。zadd,ZRANGEBYSCORE命令。
 *
 * publish/subscribe，Redis 客户端可以订阅任意数量的频道。
 *
 * redis.conf 是一个默认的配置文件。
 * Redis 新的 vm 机制，会把 Key 存放内存，Value 会存放在 swap区。
 *
 * 连接：redis-cli -h host -p port -a password
 *
 * SAVE 命令用于创建当前数据库的备份。该命令将在 redis 安装目录中创建dump.rdb文件。
 * 如果需要恢复数据，只需将备份文件 (dump.rdb) 移动到 redis 安装目录并启动服务即可。
 * 创建 redis 备份文件也可以使用命令 BGSAVE，该命令在后台执行。
 *
 * 测试性能：redis-benchmark -h 127.0.0.1 -p 6379 -t set,lpush -n 10000 -q
 *
 * Redis和Memcached区别：
 * 1、从数据结构上来说，redis在kv模式上，支持5中数据结构，String、list、hash、set、zset，并支持很多相关的计算，比如排序、阻塞等，而memcached只支持kv简单存储。
 * 2、从可靠性的角度来说，redis支持持久化，有快照和AOF两种方式，而memcached是纯的内存存储，不支持持久化的。
 * 3、从内存管理方面来说，redis也有自己的内存机制，redis采用申请内存的方式，会把带过期时间的数据存放到一起，redis理论上能够存储比物理内存更多的数据，当数据超量时，会引发swap，把冷数据刷到磁盘上。
 * 而memcached把所有的数据存储在物理内存里。memcached使用预分配池管理，会提前把内存分为多个slab，slab又分成多个不等大小的chunk，chunk从最小的开始，根据增长因子增长内存大小。
 * redis更适合做数据存储，memcached更适合做缓存，memcached在存储速度方面也会比redis这种申请内存的方式来的快。
 * 4、从数据一致性来说，memcached提供了cas命令，可以保证多个并发访问操作同一份数据的一致性问题。 redis是串行操作，所以不用考虑数据一致性的问题。
 * 5、从IO角度来说，选用的I/O多路复用模型，虽然单线程不用考虑锁等问题，但是还要执行kv数据之外的一些排序、聚合功能，复杂度比较高。memcached也选用非阻塞的I/O多路复用模型，速度更快一些。
 * 6、从线程角度来说，memcached使用多线程，主线程listen，多个worker子线程执行读写，可能会出现锁冲突。redis是单线程的(Redis 在 6.0 之后加入了多线程)，这样虽然不用考虑锁对插入修改数据造成的时间的影响，但是无法利用多核提高整体的吞吐量，只能选择多开redis来解决。
 * 7、从集群方面来说，redis天然支持高可用集群，支持主从，而memcached需要自己实现类似一致性hash的负载均衡算法才能解决集群的问题，扩展性比较低。
 * 8、Redis支持数据的备份，即master-slave模式的数据备份。
 * 9、redis集成了事务、复制、lua脚本等多种功能，功能更全。
 *
 * redis比memcached功能更全，集成更方便，但是memcached相比redis在内存、线程、IO角度来说都有一定的优势，可以利用cpu提高机器性能，在不考虑扩展性和持久性的访问频繁的情况下，只存储kv格式的数据，建议使用memcached，
 * memcached更像是个缓存，而redis更偏向与一个存储数据的系统。但是，觉得不要拿redis当数据库用。
 *
 * redis速度快的原因：
 * 1、完全基于内存，绝大部分请求是纯粹的内存操作，非常快速。
 * 2、数据结构简单，对数据操作也简单，Redis中的数据结构是专门进行设计的，大多查找和操作的时间复杂度都是O(1)。
 * 3、采用单线程，避免了不必要的上下文切换和竞争条件，也不存在多进程或者多线程导致的切换而消耗 CPU。
 * （官方FAQ表示，因为Redis是基于内存的操作，CPU不是Redis的瓶颈，Redis的瓶颈最有可能是机器内存的大小或者网络带宽。既然单线程容易实现，而且CPU不会成为瓶颈，那就顺理成章地采用单线程的方案了。）
 * 4、使用多路I/O复用模型，非阻塞IO。多路I/O复用模型是利用 select、poll、epoll 可以同时监察多个流的 I/O 事件的能力，在空闲的时候，会把当前线程阻塞掉，当有一个或多个流有 I/O 事件时，就从阻塞态中唤醒，
 * 于是程序就会轮询一遍所有的流（epoll 是只轮询那些真正发出了事件的流），并且只依次顺序的处理就绪的流，这种做法就避免了大量的无用操作。
 * 这里“多路”指的是多个网络连接，“复用”指的是复用同一个线程。采用多路 I/O 复用技术可以让单个线程高效的处理多个连接请求（尽量减少网络 IO 的时间消耗）
 * 5、使用底层模型不同，它们之间底层实现方式以及与客户端之间通信的应用协议不一样，Redis直接自己构建了VM 机制 ，因为一般的系统调用系统函数的话，会浪费一定的时间去移动和请求。
 *
 * 并不是说整个redis服务只有一个线程在工作,而是单线程来处理用户对数据的操作,还有别的线程在做其他事情.
 * 因为redis用了epoll多路复用,所以很快，而redis是单进程的,所以对于同一个客户端的命令的处理是有"顺序"的
 * nginx的每个worker也是用了epoll,非阻塞多路复用。
 *
 * 了解IO NIO：
 *  早期的BIO(Blocking IO):
 *  内核发展-同步非阻塞socket nonblock(NIO)
 *  内核发展-select多路复用NIO
 *  内核发展-mmap,epoll
 *
 * @author WangChao
 * @date 2020/04/25
 */
public class RedisTest {

    public static void redisString() {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost");
        System.out.println("连接成功");
        //设置 redis 字符串数据
        jedis.set("runoobkey", "www.runoob.com");
        // 获取存储的数据并输出
        System.out.println("redis 存储的字符串为: " + jedis.get("runoobkey"));
    }

    public static void redisList() {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost");
        System.out.println("连接成功");
        //存储数据到列表中
        jedis.lpush("site-list", "Runoob");
        jedis.lpush("site-list", "Google");
        jedis.lpush("site-list", "Taobao");
        // 获取存储的数据并输出
        List<String> list = jedis.lrange("site-list", 0, 2);
        for (int i = 0; i < list.size(); i++) {
            System.out.println("列表项为: " + list.get(i));
        }
    }

    public static void redisSet() {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost");
        System.out.println("连接成功");

        // 获取数据并输出
        Set<String> keys = jedis.keys("*");
        Iterator<String> it = keys.iterator();
        while (it.hasNext()) {
            String key = it.next();
            System.out.println(key);
        }
    }

}
