package com.tool.demo.designpattern.template;

/**
 * 模板设计模式
 *
 * @author WangChao
 * @date 2020/04/19
 */
public class TemplateDesignPattern {

    public static void main(String[] args) {
        Action robotAction = new Robot();
        Action personAction = new Person();
        Action pigAction = new Pig();

        System.out.println("------------- 机器人行为 -------------");
        robotAction.command(Action.SLEEP);
        robotAction.command(Action.WORK);

        System.out.println("------------- 人类行为 -------------");
        personAction.command(Action.EAT + Action.SLEEP + Action.WORK);

        System.out.println("------------- 猪类行为 -------------");
        pigAction.command(Action.WORK);
        pigAction.command(Action.EAT);
    }

}

