package com.tool.demo.thread.example.threa6;

/**
 * 类加载过称：加载->验证->准备->解析->初始化
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class Demo {

    private int a;
    private final int b;  // 未初始化，会报错

    private static final int d;

    // 实例被初始化的过称中
    public Demo() { // 1
        a = 20;  // 可以放到构造方法以外 // 2
        b = 10;  // 可以初始化非静态final // 3
        // 可以理解为类初始化时给a，b默认值0
    } // 4

    // 实例被初始化的过称中
    // 代码块优先与构造方法执行
    {
//		b = 20; // 可以初始化非静态final，若已经初始化，就不能再初始化
    }

    // 准备的过称中默认值0，初始化时赋值为指定的值
    private static int c = 10;

    //静态代码在类加载过程中执行
    static {
//		b = 30; // 不可以初始化非静态final
        d = 40;
    }


    private Demo demo;

    public void w() { // 5
        demo = new Demo(); // 6
    }

    public void r() {
        Demo d = demo; // 7
        int temp1 = d.a; // 8
        int temp2 = d.b; // 9
    }

}
