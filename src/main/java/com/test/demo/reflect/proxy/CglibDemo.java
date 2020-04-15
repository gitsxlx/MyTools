package com.test.demo.reflect.proxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * CglibDemo
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class CglibDemo {

    public static void main(String[] args) {
        Message realObject = new Message();  // 真实主体对象
        Enhancer enhancer = new Enhancer();  // 负责代理操作的程序类
        enhancer.setSuperclass(realObject.getClass());  // 假定一个父类
        enhancer.setCallback(new MyCglibProxy(realObject));  // 设置代理类
        Message proxyObject = (Message) enhancer.create();  // 擦行间代理类
        proxyObject.send();
    }

}

class MyCglibProxy implements MethodInterceptor {  // 拦截器配置

    private Object target;  // 保存真实主体对象

    public MyCglibProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy)
            throws Throwable {
        return method.invoke(this.target, args);
    }

}

class Message {

    public void send() {
        System.out.println("发送消息");
    }
}
