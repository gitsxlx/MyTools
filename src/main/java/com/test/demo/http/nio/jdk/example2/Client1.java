package com.test.demo.http.nio.jdk.example2;

/**
 * Client1
 *
 * @author WangChao
 * @date 2020/05/13
 */
public class Client1 {

    public static void main(String[] args) {
        new NioClient().start("Client1");
    }
}
