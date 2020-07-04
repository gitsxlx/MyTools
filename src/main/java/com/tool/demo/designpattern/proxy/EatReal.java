package com.tool.demo.designpattern.proxy;

/**
 * EatReal
 *
 * @author WangChao
 * @date 2020/04/20
 */
public class EatReal implements IEat {

    @Override
    public void get() {
        System.out.println("【真实主题】得到食物，品尝");
    }
}
