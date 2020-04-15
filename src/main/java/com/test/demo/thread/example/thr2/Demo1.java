package com.test.demo.thread.example.thr2;

/**
 * join()方法：加入线程让调用的线程先执行指定时间或者是指向完毕
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class Demo1 {

    public static void main(String[] args) {

        MyRunable2 mr2 = new MyRunable2();
        Thread t = new Thread(mr2);
        t.start();

        for (int i = 0; i < 20; i++) {
            System.out.println(Thread.currentThread().getName() + "--" + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (i == 10) {
                try {
                    t.join();//让t线程执行完毕
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}

class MyRunable2 implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println(Thread.currentThread().getName() + "--" + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
