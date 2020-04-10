package com.test.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 主类
 *
 * @author WangChao
 * @date 2020/04/06
 */
@EnableScheduling
@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    //服务启动之后被立即执行
    @Override
    public void run(String... args) throws Exception {
        System.out.println("server start!!!");
    }

}

