package com.tool.demo.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Stack;
import java.util.TreeSet;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

//List:1.可以允许重复的对象。
//     2.可以插入多个null元素。
//     3.是一个有序容器，保持了每个元素的插入顺序，输出的顺序就是插入的顺序。
//     4.常用的实现类有 ArrayList、LinkedList 和 Vector。ArrayList 最为流行，它提供了使用索引的随意访问，而 LinkedList 则对于经常需要从 List 中添加或删除元素的场合更为合适
//Set:1.不允许重复对象
//    2.无序容器，你无法保证每个元素的存储顺序，TreeSet通过 Comparator  或者 Comparable 维护了一个排序顺序。
//    3.只允许一个 null 元素
//    4.Set 接口最流行的几个实现类是 HashSet、LinkedHashSet 以及 TreeSet。最流行的是基于 HashMap 实现的 HashSet；TreeSet 还实现了 SortedSet 接口，因此 TreeSet 是一个根据其 compare() 和 compareTo() 的定义进行排序的有序容器。
//Map:1.Map不是collection的子接口或者实现类。Map是一个接口。
//    2.Map 的 每个 Entry 都持有两个对象，也就是一个键一个值，Map 可能会持有相同的值对象但键对象必须是唯一的。
//    3.TreeMap 也通过 Comparator 或者 Comparable 维护了一个排序顺序。
//    4.Map 里你可以拥有随意个 null 值但最多只能有一个 null 键。
//    5.Map 接口最流行的几个实现类是 HashMap、LinkedHashMap、Hashtable 和 TreeMap。（HashMap、TreeMap最常用）

/**
 * 集合相关
 *
 * @author WangChao
 * @date 2020/04/10
 */
public class CollectionsTest {

    //***HashMap循环删除和添加数据时需要格外注意，以防有坑***
    public static void main(String[] args) {
        //快，线程不安全，数组+链表(超过8，红黑树，单向链表，之前是在链表头插入，现在是在链表尾插入)
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("a", 1);
        hashMap.put("aa", 11);
        System.out.println(hashMap.get("a"));

        //可以变成线程安全
        Collections.synchronizedMap(hashMap);

        //四种遍历map的方法
        //方法一
        for (String key : hashMap.keySet()) {
            System.out.println("key= " + key + " and value= " + hashMap.get(key));
        }
        //方法二
        Iterator<Entry<String, Integer>> it = hashMap.entrySet().iterator();
        while (it.hasNext()) {
            Entry<String, Integer> entry = it.next();
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
        }
        //第三种：推荐，尤其是容量大时
        for (Entry<String, Integer> entry : hashMap.entrySet()) {
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
        }
        //第四种
        for (Integer v : hashMap.values()) {
            System.out.println("value= " + v);
        }

        //线程安全,比ConcurrentHashMap慢，不推荐使用
        Hashtable<String, Integer> hashtable = new Hashtable<>();
        hashtable.put("b", 2);
        System.out.println(hashtable.get("b"));

        //线程安全
        ConcurrentHashMap<String, Integer> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put("c", 3);
        System.out.println(concurrentHashMap.get("c"));

        //矢量队列，动态数组，线程安全，慢，支持序列化
        Vector<Integer> vector = new Vector<>();
        vector.add(4);
        System.out.println(vector.get(0));

        //数组队列，动态数组。随机访问效率高，随机插入、随机删除效率低，线程不安全，支持序列化
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(5);
        System.out.println(arrayList.get(0));

        //双向链表，随机访问效率低，但随机插入、随机删除效率低
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(6);
        System.out.println(linkedList.get(0));

        //栈，它继承于Vector。先进后出(FILO, First In Last Out)
        Stack<Integer> stack = new Stack<>();
        stack.push(7);
        stack.push(8);
        stack.pop();
        System.out.println(stack.get(0));

        HashSet<String> hashSet = new HashSet<>();
        TreeSet<String> treeSet = new TreeSet<>();
        treeSet.add("e");
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();
    }

}
