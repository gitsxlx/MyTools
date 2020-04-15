package com.test.demo.collection.iter;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Set遍历
 *
 * @author WangChao
 * @date 2020/04/14
 */
public class IteratorTest {

    public static void main(String[] args) {
        Set<String> all = new HashSet<>();
        all.add("Hello");
        all.add("world");
        all.add("aaa");

        Iterator<String> iterator = all.iterator();
        while (iterator.hasNext()) {
            String str = iterator.next();
            if ("world".equals(str)) {
                //all.remove("world");
                iterator.remove();
            } else {
                System.out.println(str);
            }
        }
        System.out.println(all);
    }

}
