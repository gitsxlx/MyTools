package com.tool.demo.thread.example.th7;

/**
 * PushTarget
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class PushTarget implements Runnable {

    public Tmall tmall;

    public PushTarget(Tmall tmall) {
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
