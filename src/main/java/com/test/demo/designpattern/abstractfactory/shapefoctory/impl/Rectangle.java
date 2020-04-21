package com.test.demo.designpattern.abstractfactory.shapefoctory.impl;

import com.test.demo.designpattern.abstractfactory.shapefoctory.Shape;

/**
 * Rectangle
 *
 * @author WangChao
 * @date 2020/04/20
 */
public class Rectangle implements Shape {

    @Override
    public void draw() {
        System.out.println("in Rectangle::draw() method.");
    }

}
