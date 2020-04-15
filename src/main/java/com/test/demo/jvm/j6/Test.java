package com.test.demo.jvm.j6;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 集合
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class Test {

    public static void main(String[] args) {
        String str = "123";
        System.out.println(Optional.ofNullable(str).map(s -> 1).orElse(0));
        System.out.println(Optional.empty().get());

        Map<String, Object> map = new HashMap<>();

        Collection<String> col1 = new ArrayList<>();
        Collection<String> col2 = new ArrayBlockingQueue<String>(10);
        Collection<String> col3 = new HashSet<>();

        List<String> list = new ArrayList<>();
        Set<String> set = new HashSet<>();
        Queue<Integer> queue = new ArrayBlockingQueue<Integer>(10);
    }

}
