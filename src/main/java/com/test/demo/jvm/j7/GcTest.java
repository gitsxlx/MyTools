package com.test.demo.jvm.j7;

/**
 * GC
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class GcTest {

    private Object instance;

    public GcTest() {
        byte[] m = new byte[20 * 1024 * 1024];
    }

    public static void main(String[] args) {

        GcTest t1 = new GcTest();

        GcTest t2 = new GcTest();

        t1.instance = t2;
        t2.instance = t1;

        t1 = null;
        t2 = null;

        System.gc();

    }

}
