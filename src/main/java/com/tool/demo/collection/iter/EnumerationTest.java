package com.tool.demo.collection.iter;

import java.util.Enumeration;
import java.util.Vector;

/**
 * Vector遍历
 *
 * @author WangChao
 * @date 2020/04/14
 */
public class EnumerationTest {

    public static void main(String[] args) {
        Vector<String> all = new Vector<>();
        all.add("Hello");
        all.add("world");
        all.add("aaa");

        Enumeration<String> enu = all.elements();
        while (enu.hasMoreElements()) {
            String str = enu.nextElement();
            System.out.println(str);
        }
    }

}
