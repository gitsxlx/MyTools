package com.tool.demo.proxy.jdkproxy;

import java.lang.reflect.Proxy;

/**
 * Main
 *
 * @author WangChao
 * @date 2020/04/10
 */
public class Main {

    public static void main(String[] args) {

        //代理对象
        Subject proxyClass = (Subject) Proxy
                .newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{Subject.class},
                        new MyInvocationHandler(new RealSubject()));  //真实对象

        proxyClass.sellBooks();

    }

}
