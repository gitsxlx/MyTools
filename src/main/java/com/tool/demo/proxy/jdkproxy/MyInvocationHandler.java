package com.tool.demo.proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * MyInvocationHandler
 *
 * @author WangChao
 * @date 2020/04/10
 */
public class MyInvocationHandler implements InvocationHandler {

    /**
     * 因为需要处理真实角色，所以要把真实角色传进来
     */
    Subject realSubject;

    public MyInvocationHandler(Subject realSubject) {
        this.realSubject = realSubject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("调用代理类");
        if (method.getName().equals("sellBooks")) {
            int invoke = (int) method.invoke(realSubject, args);
            System.out.println("调用的是卖书的方法");
            return invoke;
        } else {
            String string = (String) method.invoke(realSubject, args);
            System.out.println("调用的是说话的方法");
            return string;
        }
    }

}
