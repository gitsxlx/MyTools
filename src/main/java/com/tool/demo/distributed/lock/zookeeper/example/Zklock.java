package com.tool.demo.distributed.lock.zookeeper.example;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

/**
 * TODO
 *
 * @author WangChao
 * @date 2020/06/21
 */
public class Zklock implements Lock {

    private ThreadLocal<ZooKeeper> zk = new ThreadLocal<>();
    private static final String LOCK_NAME = "/LOCK";
    private ThreadLocal<String> CURRENT_NODE = new ThreadLocal<>();

    public void init() {
        if (zk.get() == null) {
            try {
                zk.set(new ZooKeeper("localhost:2181", 300, new Watcher() {
                    @Override
                    public void process(WatchedEvent event) {
                        // ..
                    }
                }));

                try {
                    Stat stat = zk.get().exists(LOCK_NAME, true);
                    if (stat == null) {
                        zk.get().create(LOCK_NAME, new byte[0], Ids.OPEN_ACL_UNSAFE,
                                CreateMode.PERSISTENT);
                    }
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void lock() {
        init();

        if (tryLock()) {
            System.out.println(Thread.currentThread().getName() + "已经获取到锁了...");
        }
    }

    @Override
    public boolean tryLock() {
        String nodeName = LOCK_NAME + "/zk_";

        try {
            CURRENT_NODE.set(zk.get().create(nodeName, new byte[0], Ids.OPEN_ACL_UNSAFE,
                    CreateMode.EPHEMERAL_SEQUENTIAL));

            List<String> list = zk.get().getChildren(LOCK_NAME, false);
            Collections.sort(list);

            String minNodeName = list.get(0);

            if (CURRENT_NODE.get().equals(LOCK_NAME + "/" + minNodeName)) {
                return true;
            } else {
                String currentNodeSimpleName = CURRENT_NODE.get()
                        .substring(CURRENT_NODE.get().lastIndexOf("/") + 1);
                Integer currentNodeIndex = list.indexOf(currentNodeSimpleName);
                String prevNodeName = list.get(currentNodeIndex - 1);

                // 阻塞
                final CountDownLatch cdl = new CountDownLatch(1);
                Stat nodeStat = zk.get().exists(LOCK_NAME + "/" + prevNodeName, new Watcher() {
                    @Override
                    public void process(WatchedEvent event) {
                        //....
                        cdl.countDown();
                        System.out.println(Thread.currentThread().getName() + "被唤醒...");
                    }
                });

                System.out.println(Thread.currentThread().getName() + "阻塞住...");
                cdl.await();
                return true;
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void unlock() {
        try {
            zk.get().delete(CURRENT_NODE.get(), -1);
            CURRENT_NODE.set(null);
            zk.get().close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
