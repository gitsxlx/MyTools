package com.tool.demo.thread.example.th6;

/**
 * Target1
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class Target1 implements Runnable {

    private Demo3 demo;

    public Target1(Demo3 demo) {
        this.demo = demo;
    }

    @Override
    public void run() {
        demo.setSignal();
    }

}
