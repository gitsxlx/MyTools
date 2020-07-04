package com.tool.demo.designpattern.abstractfactory.factory;

import com.tool.demo.designpattern.abstractfactory.colorfoctory.Color;
import com.tool.demo.designpattern.abstractfactory.shapefoctory.Shape;

/**
 * AbstractFactory
 *
 * @author WangChao
 * @date 2020/04/20
 */
public abstract class AbstractFactory {

    /**
     * getColor
     * @param color
     * @return
     */
    public abstract Color getColor(String color);

    /**
     * getShape
     * @param shape
     * @return
     */
    public abstract Shape getShape(String shape);

}
