package com.test.demo.reflect.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * MyAnnotationDemo
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class MyAnnotationDemo {

    public static void main(String[] args) {
        try {
            Method method = Message.class.getDeclaredMethod("send", String.class);  // 获得指定方法
            DefaultAnnotation annotation = method
                    .getAnnotation(DefaultAnnotation.class);  // 获取指定的Annotation
            System.out.println(annotation);
            System.out.println(annotation.title());  // 直接调用Annotation中的方法
            System.out.println(annotation.url());

            String msg = annotation.title() + "（" + annotation.url() + "）";
            method.invoke(Message.class.getDeclaredConstructor().newInstance(), msg);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

}

// 自定义Annotation
@Retention(RetentionPolicy.RUNTIME)
        // 定义Annotation的运行策略
@interface DefaultAnnotation {

    String title();

    public String url() default "www.95081.com";
}

class Message {

    @DefaultAnnotation(title = "管家帮")
    public void send(String msg) {
        System.out.println("【发送消息】" + msg);
    }
}
