package com.tool.demo.thread.example.th6;

/**
 * Target2
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class Target2 implements Runnable {

    private Demo3 demo;

    public Target2(Demo3 demo) {
        this.demo = demo;
    }

    @Override
    public void run() {
        demo.getSignal();
    }

}
