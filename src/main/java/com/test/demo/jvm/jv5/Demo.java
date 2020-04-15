package com.test.demo.jvm.jv5;

/**
 * Demo
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class Demo {

    static class Parent {

    }

    static class Child1 extends Parent {

    }

    static class Child2 extends Parent {

    }

    public void sayHello(Child1 c) {
        System.out.println("c1 is call");
    }

    public void sayHello(Child2 c) {
        System.out.println("c2 is call");
    }

    public void sayHello(Parent p) {
        System.out.println("p is call");
    }

    public static void main(String[] args) {

        Parent c1 = new Child1();  //父类引用指向子类实例
        Parent c2 = new Child2();

        Demo d = new Demo();

        d.sayHello(c1);
        d.sayHello(c2);

        // 实际类型发生改变
        Parent p = new Child1();
        p = new Child2();

        //静态类型发生变化
        d.sayHello((Child2) p);

    }

}
