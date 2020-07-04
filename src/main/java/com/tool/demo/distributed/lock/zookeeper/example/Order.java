package com.tool.demo.distributed.lock.zookeeper.example;

/**
 * 订单
 *
 * @author WangChao
 * @date 2020/06/21
 */
public class Order {

    public void createOrder() {
        System.out.println(Thread.currentThread().getName() + "创建order");
    }

}
