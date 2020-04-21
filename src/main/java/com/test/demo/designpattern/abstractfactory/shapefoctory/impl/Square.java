package com.test.demo.designpattern.abstractfactory.shapefoctory.impl;

import com.test.demo.designpattern.abstractfactory.shapefoctory.Shape;

/**
 * Square
 *
 * @author WangChao
 * @date 2020/04/20
 */
public class Square implements Shape {

    @Override
    public void draw() {
        System.out.println("in Square::draw() method.");
    }

}
