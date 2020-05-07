package com.test.demo.jvm.j8;

import java.util.Scanner;

/**
 * Scanner
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class ScannerTest {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.next();

        new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {

                }
            }
        }, "while true").start();

        sc.next();
        testWait(new Object());

    }

    private static void testWait(Object obj) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                synchronized (obj) {
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, "wait").start();
    }

}
