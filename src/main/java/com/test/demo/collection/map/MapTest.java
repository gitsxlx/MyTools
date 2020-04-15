package com.test.demo.collection.map;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * map
 *
 * @author WangChao
 * @date 2020/04/14
 */
public class MapTest {

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        //Map<String, Integer> map = new LinkedHashMap<String, Integer>();
        //Map<String, Integer> map = new Hashtable<>();
        System.out.println(map.put("one", 1));  //key不重复，返回null
        System.out.println(map.put("two", 2));  //key不重复，返回null
        System.out.println(map.put("one", 101));  //key重复，返回旧数据
        map.put(null, 0);
        map.put("zero", null);
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

}
