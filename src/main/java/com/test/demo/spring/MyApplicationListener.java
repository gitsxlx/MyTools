package com.test.demo.spring;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * MyApplicationListener
 *
 * @author WangChao
 * @date 2020/04/18
 */
//@Component
public class MyApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("7、ApplicationListener<ContextRefreshedEvent>.onApplicationEvent:"
                + contextRefreshedEvent);
    }
}