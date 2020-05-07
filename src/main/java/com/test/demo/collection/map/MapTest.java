package com.test.demo.collection.map;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * map
 *
 * @author WangChao
 * @date 2020/04/14
 */
public class MapTest {

    public static void main(String[] args) {
        //Map<String, Integer> map = new LinkedHashMap<String, Integer>();
        //Map<String, Integer> map = new Hashtable<>();
        Map<String, Integer> map = new HashMap<String, Integer>() {
            private static final String ZERO = "zero";
            {
                put(null, 0);
                put(ZERO, null);
            }
        };
        System.out.println(map.put("one", 1));  //key不重复，返回null
        System.out.println(map.put("two", 2));  //key不重复，返回null
        System.out.println(map.put("one", 101));  //key重复，返回旧数据

        System.out.println(map);  //{null=0, zero=null, one=101, two=2}
        System.out.println(map.get("one"));  //101
        System.out.println(map.get(null));  //0
        System.out.println(map.get("ten"));  //null

        //Map.Entry<String, Integer> entry;
        //KeyValueHolder t;
        //ValueHolder v;

        Set<Map.Entry<String, Integer>> set = map.entrySet();
        Iterator<Map.Entry<String, Integer>> iter = set.iterator();
        while (iter.hasNext()) {
            Map.Entry<String, Integer> me = iter.next();
            System.out.println(me.getKey());
            System.out.println(me.getValue());
        }

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }

    }


    private static Map<String, Object> map1 = new HashMap<String, Object>();
    private static Map<String, Object> map2 = new Hashtable<String, Object>();
    private static Map<String, Object> map3 = new ConcurrentHashMap<String, Object>();
    private static Map<String, Object> map4 = Collections
            .synchronizedMap(new HashMap<String, Object>());

    private static Map<String, Object> map = map4;

    public static void main1(String[] args) {

        new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    if (map.size() > 0) {
                        for (Map.Entry<String, Object> entry : map.entrySet()) {
                            System.out.println(
                                    String.format("%s: %s", entry.getKey(), entry.getValue()));
                        }
                        map.clear();
                    }
                    try {
                        Thread.sleep((new Random().nextInt(10) + 1) * 1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {

            @Override
            public void run() {

                for (int i = 1; i <= 100; i++) {
                    map.put("key" + i, "value" + i);
                    try {
                        Thread.sleep((new Random().nextInt(10) + 1) * 1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

}
