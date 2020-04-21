package com.test.demo.designpattern.strategy;

/**
 * Context
 *
 * @author WangChao
 * @date 2020/04/21
 */
public class Context {

    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public int executeStrategy(int num1, int num2) {
        return strategy.doOperation(num1, num2);
    }

}
