package com.test.demo.designpattern.abstractfactory;

import com.test.demo.designpattern.abstractfactory.factory.AbstractFactory;
import com.test.demo.designpattern.abstractfactory.factory.ColorFactory;
import com.test.demo.designpattern.abstractfactory.factory.ShapeFactory;

/**
 * FactoryProducer
 *
 * @author WangChao
 * @date 2020/04/20
 */
public class FactoryProducer {

    private static final String SHAPE = "SHAPE";
    private static final String COLOR = "COLOR";

    public static AbstractFactory getFactory(String choice) {
        if (SHAPE.equalsIgnoreCase(choice)) {
            return new ShapeFactory();
        } else if (COLOR.equalsIgnoreCase(choice)) {
            return new ColorFactory();
        }
        return null;
    }

}
