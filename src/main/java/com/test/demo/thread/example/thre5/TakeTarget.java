package com.test.demo.thread.example.thre5;

/**
 * TakeTarget
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class TakeTarget implements Runnable {

    public Shop tmall;

    public TakeTarget(Shop tmall) {
        this.tmall = tmall;
    }

    @Override
    public void run() {
        while (true) {
            tmall.take();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
