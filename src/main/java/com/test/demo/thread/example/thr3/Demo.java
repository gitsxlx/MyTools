package com.test.demo.thread.example.thr3;

/**
 * yield，表示暂停当前线程，执行其他线程(包括自身线程) 由cpu决定
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class Demo implements Runnable {

    @Override
    public void run() {

        System.out.println("first: " + Thread.currentThread().getName());
        // 暂停当前正在执行的线程对象，并执行其他线程，就是进入就绪状态
        Thread.yield();
        // 可能还会执行 本线程: 以下语句不一定紧接着上面的语句被执行，可能其他线程的先执行了
        System.out.println("second: " + Thread.currentThread().getName());

    }

    public static void main(String[] args) {
        Demo runn = new Demo();
        Thread t1 = new Thread(runn);
        Thread t2 = new Thread(runn);
        Thread t3 = new Thread(runn);

        t2.setPriority(t2.getPriority() + 1); //设置t2的线程优先级
        t1.start();
        t2.start();
        t3.start();

    }

}

