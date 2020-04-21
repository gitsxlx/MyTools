package com.test.demo.proxy.jdkproxy;

/**
 * RealSubject
 *
 * @author WangChao
 * @date 2020/04/10
 */
public class RealSubject implements Subject {

    @Override
    public int sellBooks() {
        System.out.println("卖书");
        return 1;
    }

    @Override
    public String speak() {
        System.out.println("说话");
        return "张三";
    }

}
