package com.test.demo.reflect.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * ReflectProxyDemo
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class ReflectProxyDemo {

    public static void main(String[] args) {
        IMessage msg = (IMessage) new MyProxy().bind(new MessageReal());
        msg.send();
    }

}

class MyProxy implements InvocationHandler {

    private Object target;  // 保存真实业务对象

    /**
     * 进行真实业务对象和代理业务对象之间的绑定
     * @parameter target 真实业务对象
     * @return Proxy生成的代理业务对象
     * @date 2019年8月11日
     */
    public Object bind(Object target) {
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("执行方法 " + method);
        return method.invoke(this.target, args);
    }

}

interface IMessage {  // 传统代理设计必须有接口

    public void send();  // 业务方法
}

class MessageReal implements IMessage {

    @Override
    public void send() {
        System.out.println("发送消息");
    }
}
