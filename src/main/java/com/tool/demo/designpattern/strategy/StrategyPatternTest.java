package com.tool.demo.designpattern.strategy;

/**
 * 策略设计模式
 *
 * @author WangChao
 * @date 2020/04/21
 */
public class StrategyPatternTest {

    public static void main(String[] args) {
        Context context = new Context(new OperationAdd());
        System.out.println("10 + 5 = " + context.executeStrategy(10, 5));

        context = new Context(new OperationSubstract());
        System.out.println("10 - 5 = " + context.executeStrategy(10, 5));

        context = new Context(new OperationMultiply());
        System.out.println("10 * 5 = " + context.executeStrategy(10, 5));
    }

}
