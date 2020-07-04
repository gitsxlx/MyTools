package com.tool.demo.thread.example.threa5;

/**
 * 2 happens-before 3
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class Demo {

    private volatile boolean flag;
    private volatile int a;

    public void write() {
        a = 10; // 1
        flag = true; // 2 当写一个volatile变量时，java内存模型会把该线程对应的本地内存中的共享变量值刷新到主内存
    }

    public void read() {
        if (flag) { // 3 当读一个volatile变量时，java内存模型会把当前线程对应的本地内存中的共享变量置为无效，然后从主内存中读取共享变量
            int b = a + 1; // 4
            System.out.println(b); // 5
        }
    }

}
