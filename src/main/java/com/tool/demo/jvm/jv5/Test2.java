package com.tool.demo.jvm.jv5;

/**
 * Demo2
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class Test2 {

    public void sayHello(short a) {
        System.out.println("short");
    }

    public void sayHello(int a) {
        System.out.println("int");
    }

    public void sayHello(long a) {
        System.out.println("long");
    }

    public void sayHello(char a) {
        System.out.println("char");
    }

    public void sayHello(Character a) {
        System.out.println("Character");
    }

    public void sayHello(Object a) {
        System.out.println("Object");
    }

    public void sayHello(char... a) {
        System.out.println("char...");
    }

    public static void main(String[] args) {
        new Test2().sayHello('a');
    }

}
