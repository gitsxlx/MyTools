package com.test.demo.designpattern.observer;

/**
 * Observer
 *
 * @author WangChao
 * @date 2020/04/21
 */
public abstract class Observer {

    protected Subject subject;

    public abstract void update();
}
