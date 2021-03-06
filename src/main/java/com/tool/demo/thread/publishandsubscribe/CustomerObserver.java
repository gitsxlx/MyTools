package com.tool.demo.thread.publishandsubscribe;

/**
 * 观察者实现类
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class CustomerObserver implements Observer {

    //订阅者名字
    private String name;
    private int version;

    public CustomerObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(int version) {
        this.version = version;
        System.out.println("该杂志出新版本了");
        this.buy();
    }

    public void buy() {
        System.out.println(name + "购买了第" + version + "期的杂志!");
    }

}

