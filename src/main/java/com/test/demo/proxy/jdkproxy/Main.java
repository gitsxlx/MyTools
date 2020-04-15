package com.test.demo.proxy.jdkproxy;

import java.lang.reflect.Proxy;

public class Main {

    public static void main(String[] args) {

        //代理对象
        Subject proxyClass = (Subject) Proxy
                .newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{Subject.class},
                        new MyInvocationHandler(new RealSubject()));  //真实对象

        proxyClass.sellBooks();

    }

}
