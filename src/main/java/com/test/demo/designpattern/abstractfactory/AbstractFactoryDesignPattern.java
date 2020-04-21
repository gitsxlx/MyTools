package com.test.demo.designpattern.abstractfactory;

import com.test.demo.designpattern.abstractfactory.factory.AbstractFactory;
import com.test.demo.designpattern.abstractfactory.shapefoctory.Shape;

/**
 * 抽象工厂设计模式
 *
 * @author WangChao
 * @date 2020/04/20
 */
public class AbstractFactoryDesignPattern {

    public static void main(String[] args) {

        System.out.println("start");

        AbstractFactory shapeFactory = FactoryProducer.getFactory("shape");
        if (shapeFactory != null) {
            Shape shape = shapeFactory.getShape("circle");
            if (shape != null) {
                shape.draw();
            }
        }

        System.out.println("end");

    }

}
