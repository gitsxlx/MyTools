package com.test.demo.designpattern.proxy;

/**
 * EatProxy
 *
 * @author WangChao
 * @date 2020/04/20
 */
public class EatProxy<T extends IEat> implements IEat {

    private T t;

    public EatProxy(T t) {
        this.t = t;
    }

    @Override
    public void get() {
        this.prepare();
        this.t.get();
        this.clear();
    }

    public void prepare() {
        System.out.println("【代理主题】准备食物");
    }

    public void clear() {
        System.out.println("【代理主题】收拾碗筷");
    }
}
