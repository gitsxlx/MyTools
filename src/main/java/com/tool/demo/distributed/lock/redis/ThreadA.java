package com.tool.demo.distributed.lock.redis;

/**
 * ThreadA
 *
 * @author WangChao
 * @date 2020/04/24
 */
public class ThreadA extends Thread {

    private Service service;

    public ThreadA(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.seckill();
    }
}
