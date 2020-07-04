package com.tool.demo.spring;

import org.springframework.stereotype.Component;

/**
 * BeanTest
 *
 * @author WangChao
 * @date 2020/04/18
 */
//@Component
public class BeanTest {

    public void initMethod() {
        System.out.println("5、this is init method...");
    }

    public void destroyMethod() {
        System.out.println("12、this is destroy method...");
    }

}
