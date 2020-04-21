package com.test.demo.designpattern.observer;

/**
 * OctalObserver
 *
 * @author WangChao
 * @date 2020/04/21
 */
public class OctalObserver extends Observer {

    public OctalObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("Octal String: "
                + Integer.toOctalString(subject.getState()));
    }
}
