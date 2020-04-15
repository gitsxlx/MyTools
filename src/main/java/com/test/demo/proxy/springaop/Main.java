package com.test.demo.proxy.springaop;

public class Main {

    public static void main(String[] args) {
        // DefaultAopProxyFactory:
        // 如果有接口，则使用 JDK 代理，反之使用 CGLIB

        // Spring AOP 综合两种代理方式的使用前提有会如下结论：如果目标类没有实现接口，且 class 为 final 修饰的，则不能进行 Spring AOP 编程！
    }

}
