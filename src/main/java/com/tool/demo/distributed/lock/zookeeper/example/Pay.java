package com.tool.demo.distributed.lock.zookeeper.example;

/**
 * 支付
 *
 * @author WangChao
 * @date 2020/06/21
 */
public class Pay {

    public void pay() {
        System.out.println(Thread.currentThread().getName() + "支付成功");
    }

}
