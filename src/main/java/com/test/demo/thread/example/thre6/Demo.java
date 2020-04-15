package com.test.demo.thread.example.thre6;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Demo
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class Demo {

    private static final int MAXIMUM_CAPACITY = 1 << 30;

    private static final int tableSizeFor(int c) {
        int n = c - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    public static void main(String[] args) {

        //ConcurrentHashMap<String, Object> concurrentHashMap = new ConcurrentHashMap<>();

        System.out.println(tableSizeFor(100));

    }

}
