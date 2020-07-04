package com.tool.demo.thread.example.threa4;

/**
 * Demo
 * 程序次序规则
 * 监视器规则
 * 传递性
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class Demo {

    private int value;

    public synchronized void a() { // 1 获取所
        value++; // 2
    } // 3 释放锁

    public synchronized void b() { // 4 获取所
        int a = value; // 5
        //处理其它操作
    } // 6 释放锁

}
