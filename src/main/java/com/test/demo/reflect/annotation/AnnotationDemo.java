package com.test.demo.reflect.annotation;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * AnnotationDemo
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class AnnotationDemo {

    public static void main(String[] args) {
        {
            Annotation[] annotations = IMessage.class.getAnnotations();  // 获取接口上的全部Annotation
            for (Annotation annotation : annotations) {
                System.out.println(annotation);
            }
        }

        System.out.println("----------------------");

        {
            Annotation[] annotations = MessageImpl.class.getAnnotations();
            for (Annotation annotation : annotations) {
                System.out.println(annotation);
            }
        }

        System.out.println("----------------------");

        {
            try {
                Method method = MessageImpl.class.getDeclaredMethod("send", String.class);
                Annotation[] annotations = method.getAnnotations();
                for (Annotation annotation : annotations) {
                    System.out.println(annotation);
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
            }

        }
    }

}

@FunctionalInterface
@Deprecated
interface IMessage {  // 传统代理设计必须有接口

    public void send(String msg);  // 业务方法
}

@SuppressWarnings("serial")
        // 无法在程序执行的时候获取
class MessageImpl implements IMessage, Serializable {

    @Override  // 无法在程序执行的时候获取
    public void send(String msg) {
        System.out.println("发送消息" + msg);
    }
}
