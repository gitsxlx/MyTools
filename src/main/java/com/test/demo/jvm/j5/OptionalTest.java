package com.test.demo.jvm.j5;

import java.util.Optional;

/**
 * Optional
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class OptionalTest {

    public static void main(String[] args) {

        String str = "123";
        System.out.println(Optional.ofNullable(str).map(s -> 1).orElse(0));
        System.out.println(Optional.empty().get());

    }

}
