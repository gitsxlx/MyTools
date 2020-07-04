package com.tool.demo.designpattern.observer;

/**
 * BinaryObserver
 *
 * @author WangChao
 * @date 2020/04/21
 */
public class BinaryObserver extends Observer {

    public BinaryObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("Binary String: "
                + Integer.toBinaryString(subject.getState()));
    }
}
