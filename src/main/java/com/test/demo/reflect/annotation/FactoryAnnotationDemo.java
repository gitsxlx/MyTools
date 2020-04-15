package com.test.demo.reflect.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * FactoryAnnotationDemo
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class FactoryAnnotationDemo {

    public static void main(String[] args) {
        MessageService messageService = new MessageService();
        messageService.send("www.95081.com");
    }

}

@Retention(RetentionPolicy.RUNTIME)
@interface UseMessage {

    public Class<?> clazz();
}

@UseMessage(clazz = MessageImplDemo.class)
class MessageService {

    private IMessageDemo message;

    public MessageService() {
        UseMessage use = MessageService.class.getAnnotation(UseMessage.class);
        this.message = Factory.getInstance(use.clazz());
    }

    public void send(String msg) {
        this.message.send(msg);
    }
}

class Factory {

    private Factory() {

    }

    //直接返回一个实例化对象
    public static <T> T getInstance(Class<?> clazz) {
        try {
            return (T) new MessageProxy().bind(clazz.getDeclaredConstructor().newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

class MessageProxy implements InvocationHandler {

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

    public boolean connect() {
        System.out.println("【代理操作】进行消息发送通道的连接");
        return true;
    }

    public void close() {
        System.out.println("【代理操作】进行消息发送通道的关闭");
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("执行方法 " + method);
        try {
            if (this.connect()) {
                return method.invoke(this.target, args);
            } else {
                throw new Exception("【Error】消息无法进行发送！");
            }
        } finally {
            this.close();
        }
    }

}

interface IMessageDemo {  // 传统代理设计必须有接口

    public void send(String msg);  // 业务方法
}

class MessageImplDemo implements IMessageDemo {

    @Override
    public void send(String msg) {
        System.out.println("【发送消息】" + msg);
    }
}
