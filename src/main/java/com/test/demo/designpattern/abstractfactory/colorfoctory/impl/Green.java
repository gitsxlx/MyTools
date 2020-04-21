package com.test.demo.designpattern.abstractfactory.colorfoctory.impl;

import com.test.demo.designpattern.abstractfactory.colorfoctory.Color;

/**
 * Green
 *
 * @author WangChao
 * @date 2020/04/20
 */
public class Green implements Color {

    @Override
    public void fill() {
        System.out.println("in Green::color() method.");
    }

}
