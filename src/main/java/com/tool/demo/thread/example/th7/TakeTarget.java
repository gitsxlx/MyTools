package com.tool.demo.thread.example.th7;

/**
 * TakeTarget
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class TakeTarget implements Runnable {

    public Tmall tmall;

    public TakeTarget(Tmall tmall) {
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
