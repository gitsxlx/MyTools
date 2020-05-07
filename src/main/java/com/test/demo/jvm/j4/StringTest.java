package com.test.demo.jvm.j4;

/**
 * intern
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class StringTest {

    public static void main(String[] args) {

        String s1 = "abc";
        String s2 = "abc";

        System.out.println(s1 == s2);

        String s3 = new String("abc");

        System.out.println(s1 == s3);

        System.out.println(s1 == s3.intern());

    }

}
