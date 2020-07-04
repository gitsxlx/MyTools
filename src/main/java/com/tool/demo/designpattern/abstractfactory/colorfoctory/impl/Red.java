package com.tool.demo.designpattern.abstractfactory.colorfoctory.impl;

import com.tool.demo.designpattern.abstractfactory.colorfoctory.Color;

/**
 * Red
 *
 * @author WangChao
 * @date 2020/04/20
 */
public class Red implements Color {

    @Override
    public void fill() {
        System.out.println("in Red::color() method.");
    }

}
