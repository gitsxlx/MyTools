package com.test.demo.designpattern.abstractfactory.colorfoctory.impl;

import com.test.demo.designpattern.abstractfactory.colorfoctory.Color;

/**
 * Blue
 *
 * @author WangChao
 * @date 2020/04/20
 */
public class Blue implements Color {

    @Override
    public void fill() {
        System.out.println("in Blue::color() method.");
    }

}
