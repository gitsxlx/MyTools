package com.test.demo.designpattern.strategy;

/**
 * OperationSubstract
 *
 * @author WangChao
 * @date 2020/04/21
 */
public class OperationSubstract implements Strategy {

    @Override
    public int doOperation(int num1, int num2) {
        return num1 - num2;
    }
}
