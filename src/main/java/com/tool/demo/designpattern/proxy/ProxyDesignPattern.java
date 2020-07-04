package com.tool.demo.designpattern.proxy;

/**
 * 代理设计模式
 *
 * @author WangChao
 * @date 2020/04/20
 */
public class ProxyDesignPattern {

    public static void main(String[] args) {
        IEat eat = new EatProxy(new EatReal());  //这块最好使用工厂设计模式
        eat.get();
    }
}
