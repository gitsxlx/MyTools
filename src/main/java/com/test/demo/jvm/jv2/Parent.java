package com.test.demo.jvm.jv2;

/**
 * Parent
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class Parent {

    public static int a = 1;

    static {
        a = 2;
    }

    static class Sub extends Parent {

        public static int b = a;
    }

    public static void main(String[] args) {
        System.out.println(Sub.b);  //2
    }

}
