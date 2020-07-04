package com.tool.demo.reflect.example.vo;

import com.tool.demo.reflect.example.abs.AbstractBase;
import com.tool.demo.reflect.example.service.IChannelService;
import com.tool.demo.reflect.example.service.IMessageService;

/**
 * Person
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class Person extends AbstractBase implements IChannelService, IMessageService {

    private String name;
    private int age;

    public Person() {
    }

    /**
     * @param name
     * @param age
     */
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person [name=" + name + ", age=" + age + "]";
    }

    @Override
    public void send() {
        if (this.connect()) {
            System.out.println("信息发送");
        }
    }

    @Override
    public boolean connect() {
        return true;
    }

}
