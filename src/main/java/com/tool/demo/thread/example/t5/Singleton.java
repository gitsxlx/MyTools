package com.tool.demo.thread.example.t5;

/**
 * Singleton
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class Singleton {

    private Singleton() {

    }

    private static Singleton singleton = new Singleton();

    public static Singleton getSingleton() {
        return singleton;
    }

}
