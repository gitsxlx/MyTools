package com.test.demo.designpattern.template;

/**
 * Pig
 *
 * @author WangChao
 * @date 2020/04/20
 */
public class Pig extends Action {

    @Override
    public void eat() {
        System.out.println("吃食槽里的剩饭。");
    }

    @Override
    public void sleep() {
        System.out.println("倒地就睡。");
    }

    @Override
    public void work() {
    }

}
