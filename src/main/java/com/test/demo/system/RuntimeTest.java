package com.test.demo.system;

import java.io.IOException;

/**
 * Runtime 单例设计模式实现
 *
 * @author WangChao
 * @date 2020/05/19
 */
public class RuntimeTest {

    public static void main(String[] args) {
        // 获取当前程序的runtime
        Runtime runtime = Runtime.getRuntime();

        // 返回可用于Java虚拟机的处理器数量
        System.out.println("可用于Java虚拟机的处理器数量:" + runtime.availableProcessors());
        // 返回Java虚拟机将尝试使用的最大内存量
        System.out.println("Java虚拟机将尝试使用的最大内存量:" + runtime.maxMemory());
        // 返回Java虚拟机中的可用内存量
        System.out.println("Java虚拟机中的可用内存量:" + runtime.freeMemory());
        // 返回Java虚拟机中的内存总量
        System.out.println("Java虚拟机中的内存总量:" + runtime.totalMemory());

        // 在单独的进程中执行指定的字符串命令
        try {
            //打开记事本
            runtime.exec("notepad.exe");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
