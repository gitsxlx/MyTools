package com.tool.demo.thread.example.threa3;

/**
 * Demo
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class Demo {

    private int a, b, c;

    /**
     * 1 happens before 2
     * 2 happens before 3
     * 3 happens before 4
     * ...
     */
    public void a() {

        a = 2; // 1
        b = 10; // 2

        c = a + b; // 3

        System.out.println(c);  // 4
    }

    public static void main(String[] args) {
        new Demo().a();
    }

}
