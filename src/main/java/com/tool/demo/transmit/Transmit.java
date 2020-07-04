package com.tool.demo.transmit;

/**
 * 值传递，引用传递
 *
 * @author WangChao
 * @date 2020/04/11
 */
public class Transmit {

    private static String country = "sss";

    //1、基本类型作为参数传递时，是传递值的拷贝，无论你怎么改变这个拷贝，原值是不会改变的
    //2、对象作为参数传递时，是把对象在内存中的地址拷贝了一份传给了参数。
    public static void main(String[] args) {
        Employee emp = new Employee();  // 声明并实例化对象
        emp.setId(1);
        emp.setName("aa");
        emp.setSalary(2000.00);
        System.out.println(emp);  // Employee [id=1, name=aa, salary=2000.0]

        Employee emp1 = emp;  // 引用传递
        emp1.setId(2);
        System.out.println(emp);  //Employee [id=2, name=aa, salary=2000.0]

        change(emp);  // 引用传递,等价于：Employee temp = emp;
        System.out.println(emp);  //Employee [id=3, name=aa, salary=2000.0]

        int num = 1;
        System.out.println("changeNum()方法调用之前：num = " + num);  // changeNum()方法调用之前：num = 1
        changeNum(num);  // 值传递
        System.out.println("changeNum()方法调用之后：num = " + num);  // changeNum()方法调用之后：num = 1

        Person p1 = new Person();
        System.out.println(p1);  // tools.Person@70dea4e
        changeObject(p1);  // 值传递
        System.out.println(p1);  // tools.Person@70dea4e

        new Transmit().aaa();
    }

    private static void change(Employee temp) {
        temp.setId(3);
    }

    public static void changeNum(int x) {
        x = 2;
    }

    private static void changeObject(Person p2) {
        p2 = new Person();
    }

    public void aaa() {
        System.out.println(this.country);  // this调用静态属性
    }

}

class Employee {

    private Integer id;  // 成员属性私有化，封装

    private String name;

    private Double salary;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", salary=" + salary + "]";
    }

}

class Person {

    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

