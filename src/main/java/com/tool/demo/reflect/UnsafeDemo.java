package com.tool.demo.reflect;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

/**
 * Unsafe
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class UnsafeDemo {

    public static void main(String[] args) throws Exception {
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);  // 解除封装
        Unsafe obj = (Unsafe) field.get(null); // static 属性不需要传递实例化对象
        // 利用Unsafe类绕过了JVM管理机制，可以在没有实例化对象的情况下获取一个类实例化对象
        Singleton singleton = (Singleton) obj.allocateInstance(Singleton.class);
        singleton.print();
    }

}

class Singleton {

    private Singleton() {
        System.out.println("****Singleton类构造****");
    }

    public void print() {
        System.out.println("www.95081.com");
    }

}
