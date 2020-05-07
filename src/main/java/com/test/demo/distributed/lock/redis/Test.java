package com.test.demo.distributed.lock.redis;

/**
 * Test
 *
 * @author WangChao
 * @date 2020/04/24
 */
public class Test {

    public static void main(String[] args) {
        Service service = new Service();
        for (int i = 0; i < 50; i++) {
            ThreadA threadA = new ThreadA(service);
            threadA.start();
        }
    }

}
