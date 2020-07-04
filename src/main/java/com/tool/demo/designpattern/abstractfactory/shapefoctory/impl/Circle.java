package com.tool.demo.designpattern.abstractfactory.shapefoctory.impl;

import com.tool.demo.designpattern.abstractfactory.shapefoctory.Shape;

/**
 * Circle
 *
 * @author WangChao
 * @date 2020/04/20
 */
public class Circle implements Shape {

    @Override
    public void draw() {
        System.out.println("in Circle::draw() method.");
    }

}
