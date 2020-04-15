package com.test.demo.reflect;

import java.lang.reflect.Method;

class Message {

}

/**
 * ClassLoaderDemo
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class ClassLoaderDemo {

    public static void main1(String[] args) {
        Class<?> clazz = Message.class;
        System.out.println(clazz.getClassLoader());  // 获取当前类加载器
        System.out.println(clazz.getClassLoader().getParent());
        System.out.println(clazz.getClassLoader().getParent().getParent());

        //sun.misc.Launcher$AppClassLoader@6d06d69c
        //sun.misc.Launcher$ExtClassLoader@70dea4e
        //null
    }

    public static void main(String[] args) throws Exception {
        MyClassLoader classLoader = new MyClassLoader();
        Class<?> cls = classLoader.loadData("reflect.util.Message");
        System.out.println(cls);  // class reflect.util.Message
        System.out.println(cls.getClassLoader());  // 自定义类加载器最后执行 reflect.MyClassLoader@5c647e05
        System.out.println(
                cls.getClassLoader().getParent());  // sun.misc.Launcher$AppClassLoader@6d06d69c
        System.out.println(cls.getClassLoader().getParent()
                .getParent());  // sun.misc.Launcher$ExtClassLoader@55f96302
        Object obj = cls.getDeclaredConstructor().newInstance();
        Method method = cls.getMethod("send");
        method.invoke(obj);  // www.95081.com
    }

}
