package com.test.demo.thread.example.t2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Demo7
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class Demo7 {

    public int add(List<Integer> values) {
        //return values.parallelStream().mapToInt(a -> a).sum();

        values.parallelStream().forEach(System.out::println);
        //values.parallelStream().forEachOrdered(System.out::println);

        return 0;
    }

    public static void main(String[] args) {

        List<Integer> values = Arrays.asList(10, 20, 30, 40);
        int res = new Demo7().add(values);
        System.out.println("计算结果为：" + res);

    }

}
