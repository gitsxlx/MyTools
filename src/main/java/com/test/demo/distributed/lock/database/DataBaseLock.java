package com.test.demo.distributed.lock.database;

/**
 * 基于数据库实现分布式锁
 *
 * 说明：代码仅供参考，不一定完全适用于生成环境
 *
 * 优点：
 * 1.理解简单
 * 缺点：
 * 1.db操作性能较差，并且有锁表的风险；
 * 2.非阻塞操作失败后，需要轮询，占用cpu资源;
 * 3.长时间不commit或者长时间轮询，可能会占用较多连接资源；
 *
 * @author WangChao
 * @date 2020/04/24
 */
public class DataBaseLock {

    // 核心思想：在数据库中创建一个表，表中包含方法名等字段，并在方法名字段上创建唯一索引，想要执行某个方法，就使用这个方法名向表中插入数据，成功插入则获取锁，执行完成后删除对应的行数据释放锁。

    //1、创建一个表
    /*DROP TABLE IF EXISTS `method_lock`;
    CREATE TABLE `method_lock` (
            `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
            `method_name` varchar(64) NOT NULL COMMENT '锁定的方法名',
            `desc` varchar(255) NOT NULL COMMENT '备注信息',
            `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
        PRIMARY KEY (`id`),
        UNIQUE KEY `uidx_method_name` (`method_name`) USING BTREE
    ) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='锁定中的方法';*/

    //2、想要执行某个方法，就使用这个方法名向表中插入数据：
    /*INSERT INTO method_lock (method_name, desc) VALUES ('methodName', '测试的methodName');
    因为我们对method_name做了唯一性约束，这里如果有多个请求同时提交到数据库的话，数据库会保证只有一个操作可以成功，那么我们就可以认为操作成功的那个线程获得了该方法的锁，可以执行方法体内容。*/

    //3、成功插入则获取锁，执行完成后删除对应的行数据释放锁：
    /*delete from method_lock where method_name ='methodName';*/

    //注意：这只是使用基于数据库的一种方法，使用数据库实现分布式锁还有很多其他的玩法！

    /*优化:
    1、因为是基于数据库实现的，数据库的可用性和性能将直接影响分布式锁的可用性及性能，所以，数据库需要双机部署、数据同步、主备切换；
    2、不具备可重入的特性，因为同一个线程在释放锁之前，行数据一直存在，无法再次成功插入数据，所以，需要在表中新增一列，用于记录当前获取到锁的机器和线程信息，在再次获取锁的时候，先查询表中机器和线程信息是否和当前机器和线程相同，若相同则直接获取锁；
    3、没有锁失效机制，因为有可能出现成功插入数据后，服务器宕机了，对应的数据没有被删除，当服务恢复后一直获取不到锁，所以，需要在表中新增一列，用于记录失效时间，并且需要有定时任务清除这些失效的数据；
    4、不具备阻塞锁特性，获取不到锁直接返回失败，所以需要优化获取逻辑，循环多次去获取。
    5、在实施的过程中会遇到各种不同的问题，为了解决这些问题，实现方式将会越来越复杂；依赖数据库需要一定的资源开销，性能问题需要考虑。*/

}
