package com.test.demo.collection.set;

import java.util.Set;
import java.util.TreeSet;

/**
 * TreeSet
 *
 * @author WangChao
 * @date 2020/04/14
 */
public class TreeSetTest {

    public static void main(String[] args) {
        Set<Person1> all = new TreeSet<>();
        all.add(new Person1("张三", 19));
        all.add(new Person1("李四", 19));
        all.add(new Person1("王五", 20));
        all.add(new Person1("王五", 20));
        all.add(new Person1("小青", 78));
        all.forEach(System.out::println);
    }

}

class Person1 implements Comparable<Person1> {

    private String name;
    private int age;

    public Person1(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "姓名：" + this.name + "、年龄：" + this.age;
    }

    @Override
    public int compareTo(Person1 o) {
        if (this.age < o.age) {
            return -1;
        } else if (this.age > o.age) {
            return 1;
        } else {
            return this.name.compareTo(o.name);
        }
    }

}
