package com.test.demo.designpattern.abstractfactory.factory;

import com.test.demo.designpattern.abstractfactory.colorfoctory.Color;
import com.test.demo.designpattern.abstractfactory.shapefoctory.Shape;
import com.test.demo.designpattern.abstractfactory.shapefoctory.impl.Circle;
import com.test.demo.designpattern.abstractfactory.shapefoctory.impl.Rectangle;
import com.test.demo.designpattern.abstractfactory.shapefoctory.impl.Square;

/**
 * ShapeFactory
 *
 * @author WangChao
 * @date 2020/04/20
 */
public class ShapeFactory extends AbstractFactory {

    @Override
    public Color getColor(String color) {
        return null;
    }

    @Override
    public Shape getShape(String shape) {
        if (shape == null) {
            return null;
        }
        if (shape.equalsIgnoreCase("CIRCLE")) {
            return new Circle();
        } else if (shape.equalsIgnoreCase("RECTANGLE")) {
            return new Rectangle();
        } else if (shape.equalsIgnoreCase("SQUARE")) {
            return new Square();
        }
        return null;
    }

}
