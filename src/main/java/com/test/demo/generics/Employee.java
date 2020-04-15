package com.test.demo.generics;

/**
 * Employee
 *
 * @author WangChao
 * @date 2020/04/11
 */
public class Employee {

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
