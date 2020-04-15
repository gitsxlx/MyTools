package com.test.demo.collection.set;

import java.util.HashSet;
import java.util.Set;

class Person {

    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + age;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Person other = (Person) obj;
        if (age != other.age) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "姓名：" + this.name + "、年龄：" + this.age;
    }

}

/**
 * HashSet
 *
 * @author WangChao
 * @date 2020/04/14
 */
public class HashSetTest {

    public static void main(String[] args) {
        Set<Person> all = new HashSet<>();
        all.add(new Person("张三", 19));
        all.add(new Person("李四", 19));
        all.add(new Person("王五", 20));
        all.add(new Person("王五", 20));
        all.add(new Person("小青", 78));
        all.forEach(System.out::println);
    }

}
