package com.tool.demo;

import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;
import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 主类
 *
 * @author WangChao
 * @date 2020/04/06
 */
@EnableFeignClients
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

    //AbstractLoadBalancerRule
    class ARule implements IRule {

        @Override
        public Server choose(Object o) {
            return null;
        }

        @Override
        public void setLoadBalancer(ILoadBalancer iLoadBalancer) {

        }

        @Override
        public ILoadBalancer getLoadBalancer() {
            return null;
        }
    }

    /**
     * 匿名内部类的代码块
     */
    private final static Map<Integer, String> hashMap = new HashMap<Integer, String>() {
        private static final long serialVersionUID = 1L;

        {
            put(1, "1");
            put(2, "2");
        }
    };

}

