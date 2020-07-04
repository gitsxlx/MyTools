package com.tool.demo.generics;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

/**
 * 泛型测试
 *
 * @author WangChao
 * @date 2020/04/14
 */
public class Ceshi {

    private static String b;

    public static void main(String[] args) {
        System.out.println("main " + b);
        int[] arr = {1, 2};
        Integer[] intArray = {1, 2, 3, 4, 5};
        //aa(arr);  //泛型要求包容的是对象类型，而基本数据类型在Java中不属于对象
        System.out.println(aa(intArray));
    }

    {
        String a = "a";
        System.out.println("构造代码块 " + a);
    }

    static {
        b = "b";
        System.out.println("静态代码块 " + b);
    }

    //<? extends T>表示该通配符所代表的类型是T类型的子类
    //<? super T>表示该通配符所代表的类型是T类型的父类
    public static <T> T aa(T[] t) {
        System.out.println("泛型方法 " + t);
        return t[0];
    }

    //<? extends T>表示该通配符所代表的类型是T类型的子类
    //<? super T>表示该通配符所代表的类型是T类型的父类
    public static <T extends List> T bb(T[] t) {
        System.out.println("泛型方法 " + t);
        return t[0];
    }

    //类型通配符一般是使用?代替具体的类型参数
    public static void getUperNumber(List<? extends Number> list) {
        Class<? extends List> clazz = list.getClass();
        System.out.println("data :" + list.get(0));
    }

}

//泛型类
class Box<E extends Employee> {

    public E cc(E e) {
        return e;
    }

    //函数式接口
    public <U> CompletableFuture<U> thenApplyAsync(Function<? super E, ? extends U> fn) {
        return new CompletableFuture();
    }

}

//泛型接口
interface Cool<K extends Employee, V extends Student> {

    V cc(K k, V v);
}


