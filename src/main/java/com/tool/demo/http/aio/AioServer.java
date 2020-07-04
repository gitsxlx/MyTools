package com.tool.demo.http.aio;

/**
 * AioServer
 * 异步非阻塞式IO，也就是说你的客户端在进行读写操作的时候，只需要给服务器发送一个请求，不用一直等待回答就可以去做其他的事了。
 *
 * @author WangChao
 * @date 2020/05/14
 */
public class AioServer {

    public static void main(String[] args) {
        new AioServerHandle().start();
        try {
            Thread.sleep(10000000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
