package com.test.demo.http.aio;

/**
 * AioClient
 *
 * @author WangChao
 * @date 2020/05/14
 */
public class AioClient {

    public static void main(String[] args) {
        new AioClientHandle().start();
        try {
            Thread.sleep(100000000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
