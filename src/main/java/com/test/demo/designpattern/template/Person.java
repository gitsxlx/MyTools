package com.test.demo.designpattern.template;

/**
 * Person
 *
 * @author WangChao
 * @date 2020/04/20
 */
public class Person extends Action {

    @Override
    public void eat() {
        System.out.println("饿的时候安静的坐下吃饭。");
    }

    @Override
    public void sleep() {
        System.out.println("安静的躺下睡觉。");
    }

    @Override
    public void work() {
        System.out.println("有想法的工作。");
    }

}
