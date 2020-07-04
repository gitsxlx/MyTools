package com.tool.demo.distributed.lock.zookeeper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * 基于Zookeeper实现分布式锁
 *
 * 说明：代码仅供参考，不一定完全适用于生产环境
 *
 * 优点：
 * 1.可靠性更好
 * 缺点：
 * 1.性能不如redis实现，主要原因是写操作（获取锁释放锁）都需要在Leader上执行，然后同步到follower。
 *
 * @author WangChao
 * @date 2020/04/24
 */
public class ZookeeperLock {

    /*1、ZooKeeper是一个为分布式应用提供一致性服务的开源组件，它内部是一个分层的文件系统目录树结构，规定同一个目录下只能有一个唯一文件名。基于ZooKeeper实现分布式锁的步骤如下：
        ①、创建一个目录lock；
        ②、线程A想获取锁就在lock目录下创建临时顺序节点；
        ③、获取lock目录下所有的子节点，然后获取比自己小的兄弟节点，如果不存在，则说明当前线程顺序号最小，获得锁；
        ④、线程B获取所有节点，判断自己不是最小节点，设置监听比自己次小的节点；
        ⑤、线程A处理完，删除自己的节点，线程B监听到变更事件，判断自己是不是最小的节点，如果是则获得锁。*/

    //2、这里推荐一个Apache的开源库Curator，它是一个ZooKeeper客户端，Curator提供的InterProcessMutex是分布式锁的实现，acquire方法用于获取锁，release方法用于释放锁。

    private CuratorFramework client;
    private InterProcessMutex mutex;
    private static final String ZK_URL = "localhost:2181";
    private static final String LOCK_PATH = "/lock";
    private static final Long EXPIRE_TIME = 20000L;

    {
        // 重试策略
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        client = CuratorFrameworkFactory
                .builder()
                .connectString(ZK_URL)
                .sessionTimeoutMs(5000)  // 会话超时时间，默认60000ms
                .connectionTimeoutMs(10000) // 连接超时时间，默认15000ms
                .retryPolicy(retryPolicy) // 重试策略
                // .namespace("base") // 名称空间，如果这里设置了名称空间，那么所有路径都将预先指定名称空间
                .build();
        client.start();

        mutex = new InterProcessMutex(client, LOCK_PATH);
    }

    /**
     * 获取锁
     * @return
     */
    public boolean doLock() {
        try {
            return mutex.acquire(EXPIRE_TIME, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 解锁
     * @return
     */
    public boolean unLock() {
        try {
            mutex.release();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 50; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    boolean flag = false;
                    ZookeeperLock lock = new ZookeeperLock();
                    try {
                        flag = lock.doLock();
                        if (flag) {
                            System.out.println(Thread.currentThread().getId() + "获得锁");
                            Thread.sleep(2000L);
                        } else {
                            System.out.println(Thread.currentThread().getId() + "获得锁失败");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        if (flag) {
                            try {
                                lock.unLock();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            });
        }
    }

}
