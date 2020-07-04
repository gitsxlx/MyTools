package com.tool.demo.designpattern.template;

/**
 * Robot
 *
 * @author WangChao
 * @date 2020/04/20
 */
public class Robot extends Action {

    @Override
    public void eat() {
        System.out.println("机器人充电。");
    }

    @Override
    public void sleep() {
    }

    @Override
    public void work() {
        System.out.println("机器人按照固定套路工作。");
    }

}
