package com.test.demo.designpattern.factory;

/**
 * 工厂设计模式
 *
 * @author WangChao
 * @date 2020/04/20
 */
public class FactoryDesignPattern {

    public static void main(String[] args) {
        IFood food = Factory.getInstance("bread");
        food.eat();
        IFood f = Factory.getInstance("milk");
        f.eat();
    }

}
