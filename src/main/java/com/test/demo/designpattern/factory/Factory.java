package com.test.demo.designpattern.factory;

/**
 * Factory
 *
 * @author WangChao
 * @date 2020/04/20
 */
public class Factory {

    private static final String BREAD = "bread";
    private static final String MILK = "milk";

    public static IFood getInstance(String className) {
        if (BREAD.equals(className)) {
            return new Bread();
        } else if (MILK.equals(className)) {
            return new Milk();
        }
        return null;
    }
}
