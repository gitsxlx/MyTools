package com.test.demo.thread.publishandsubscribe;

/**
 * 主题接口
 *
 * @author WangChao
 * @date 2020/04/15
 */
public interface Subject {

    /**
     * 添加观察者
     * @param obj
     */
    void addObserver(Observer obj);

    /**
     * 移除观察者
     * @param obj
     */
    void deleteObserver(Observer obj);

    /**
     * 当主题方法改变时,这个方法被调用,通知所有的观察者
     */
    void notifyObserver();

}
