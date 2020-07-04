package com.tool.demo.designpattern.strategy;

/**
 * OperationAdd
 *
 * @author WangChao
 * @date 2020/04/21
 */
public class OperationAdd implements Strategy {

    @Override
    public int doOperation(int num1, int num2) {
        return num1 + num2;
    }
}
