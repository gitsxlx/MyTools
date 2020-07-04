package com.tool.demo.collection.iter;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * List遍历
 *
 * @author WangChao
 * @date 2020/04/14
 */
public class ListIteratorDemo {

    public static void main(String[] args) {
        List<String> all = new ArrayList<>();
        all.add("Hello");
        all.add("world");
        all.add("aaa");

        for (String str : all) {
            System.out.println(str);
        }

        ListIterator<String> iterator = all.listIterator();

        System.out.print("由前向后输出：");
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + "、");
        }

        System.out.print("\n由后向前输出：");
        while (iterator.hasPrevious()) {
            System.out.print(iterator.previous() + "、");
        }
    }

}
