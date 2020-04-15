package com.test.demo.thread.example.t8;

/**
 * User
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class User {

    private String name;

    public volatile int age;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }


}
