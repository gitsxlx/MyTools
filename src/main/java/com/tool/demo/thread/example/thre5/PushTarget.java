package com.tool.demo.thread.example.thre5;

/**
 * PushTarget
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class PushTarget implements Runnable {

    public Shop tmall;

    public PushTarget(Shop tmall) {
        this.tmall = tmall;
    }

    @Override
    public void run() {
        while (true) {
            tmall.push();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
