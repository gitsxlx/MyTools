package com.test.demo.thread.example.t5;

/**
 * Main
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class Main {

    public static void main(String[] args) {
        Singleton2 s1 = Singleton2.getSingleton();
        Singleton2 s2 = Singleton2.getSingleton();
        Singleton2 s3 = Singleton2.getSingleton();
        Singleton2 s4 = Singleton2.getSingleton();

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
        System.out.println(s4);

    }

}
