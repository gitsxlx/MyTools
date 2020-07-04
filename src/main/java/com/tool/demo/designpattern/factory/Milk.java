package com.tool.demo.designpattern.factory;

/**
 * Milk
 *
 * @author WangChao
 * @date 2020/04/20
 */
public class Milk implements IFood {

    @Override
    public void eat() {
        System.out.println("喝牛奶");
    }
}
