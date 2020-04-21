package com.test.demo.proxy.cglibproxy;

/**
 * 它为没有实现接口的类提供代理，为JDK的动态代理提供了很好的补充。
 *
 * @author WangChao
 * @date 2020/04/10
 */
public class Main {

    public static void main(String[] args) {
        // 生成 Cglib 代理类
        Engineer engineerProxy = (Engineer) CglibProxy.getProxy(new Engineer());
        // 调用相关方法
        engineerProxy.eat();
    }

}
