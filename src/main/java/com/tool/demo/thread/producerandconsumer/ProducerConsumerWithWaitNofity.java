package com.tool.demo.thread.producerandconsumer;

/**
 * 使用wait和notify实现生产者和消费者
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class ProducerConsumerWithWaitNofity {

    public static void main(String[] args) {
        Resource resource = new Resource();

        //生产者线程
        ProducerThread p1 = new ProducerThread(resource);
        ProducerThread p2 = new ProducerThread(resource);
        ProducerThread p3 = new ProducerThread(resource);

        //消费者线程
        ConsumerThread c1 = new ConsumerThread(resource);
        ConsumerThread c2 = new ConsumerThread(resource);
        ConsumerThread c3 = new ConsumerThread(resource);

        p1.start();
        p2.start();
        p3.start();
        c1.start();
        c2.start();
        c3.start();
    }

}

/**
 * 资源
 * @author
 *
 */
class Resource {

    //数量
    private int num = 0;
    //大小
    private int size = 10;

    /**
     * 移除
     */
    public synchronized void remove() {
        if (num > 0) {
            num--;
            System.out.println("线程名称:" + Thread.currentThread().getName() +
                    "，移除之后数量:" + num);
            notifyAll(); //唤醒等待线程
        } else {
            try {
                //等待
                wait();
                System.out.println("线程名称:" + Thread.currentThread().getName() + "等待");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 添加
     */
    public synchronized void add() {
        if (num < size) {
            num++;
            System.out.println(Thread.currentThread().getName() + "，增加之后数量:"
                    + num);
            //唤醒等待线程
            notifyAll();
        } else {
            try {
                wait();//等待
                System.out.println(Thread.currentThread().getName() + "等待");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * 消费线程
 */
class ConsumerThread extends Thread {

    private Resource resource;

    public ConsumerThread(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resource.remove();
        }
    }
}

/**
 * 生成线程
 */
class ProducerThread extends Thread {

    private Resource resource;

    public ProducerThread(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resource.add();
        }
    }

}
