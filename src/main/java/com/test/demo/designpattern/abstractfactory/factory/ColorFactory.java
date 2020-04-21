package com.test.demo.designpattern.abstractfactory.factory;

import com.test.demo.designpattern.abstractfactory.colorfoctory.Color;
import com.test.demo.designpattern.abstractfactory.colorfoctory.impl.Blue;
import com.test.demo.designpattern.abstractfactory.colorfoctory.impl.Green;
import com.test.demo.designpattern.abstractfactory.colorfoctory.impl.Red;
import com.test.demo.designpattern.abstractfactory.shapefoctory.Shape;

/**
 * ColorFactory
 *
 * @author WangChao
 * @date 2020/04/20
 */
public class ColorFactory extends AbstractFactory {

    @Override
    public Color getColor(String color) {
        if (color == null) {
            return null;
        }
        if (color.equalsIgnoreCase("RED")) {
            return new Red();
        } else if (color.equalsIgnoreCase("GREEN")) {
            return new Green();
        } else if (color.equalsIgnoreCase("BLUE")) {
            return new Blue();
        }
        return null;
    }

    @Override
    public Shape getShape(String shape) {
        return null;
    }

}
