package com.test.demo.designpattern.strategy;

/**
 * TODO
 *
 * @author WangChao
 * @date 2020/04/21
 */
public class OperationMultiply implements Strategy {

    @Override
    public int doOperation(int num1, int num2) {
        return num1 * num2;
    }
}
