package com.tool.demo.thread.example.thre3;

import java.util.AbstractCollection;
import java.util.AbstractList;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * Demo
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class Demo {

    public static void main(String[] args) {

        //Vector继承AbstractList<E>抽象类（->继承AbstractCollection<E>抽象类->实现Collection<E>接口->实现Iterable<T>接口），实现List<E>接口
        Vector<String> v = new Vector<>();
        v.add("e"); // synchronized同步方法

        //ArrayList继承AbstractList<E>抽象类（->继承AbstractCollection<E>抽象类->实现Collection<E>接口->实现Iterable<T>接口），实现List<E>接口
        ArrayList<String> s = new ArrayList<>(100);
        Collections.synchronizedList(s);  // synchronized同步代码块
        s.add("b");

        //Hashtable继承Dictionary<K,V>抽象类，实现Map<K,V>接口
        Hashtable<String, Object> hashtable = new Hashtable<>();
        hashtable.put("aa", 111); // synchronized同步方法

        //HashMap继承AbstractMap<K,V>抽象类，实现Map<K,V>接口
        HashMap<String, Object> hashMap = new HashMap<>();
        Collections.synchronizedMap(hashMap);  // synchronized同步代码块

    }

}
