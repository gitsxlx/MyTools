package com.test.demo.designpattern.factory;

/**
 * Bread
 *
 * @author WangChao
 * @date 2020/04/20
 */
public class Bread implements IFood {

    @Override
    public void eat() {
        System.out.println("吃面包");
    }
}
