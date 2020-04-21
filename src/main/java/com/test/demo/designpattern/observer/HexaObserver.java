package com.test.demo.designpattern.observer;

/**
 * HexaObserver
 *
 * @author WangChao
 * @date 2020/04/21
 */
public class HexaObserver extends Observer {

    public HexaObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("Hex String: "
                + Integer.toHexString(subject.getState()).toUpperCase());
    }
}
