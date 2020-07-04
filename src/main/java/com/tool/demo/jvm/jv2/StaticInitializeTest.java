package com.tool.demo.jvm.jv2;

/**
 * Demo
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class StaticInitializeTest {

    static {
        a = 0;
        //System.out.println(a);
    }

    static int a = 1;

}
