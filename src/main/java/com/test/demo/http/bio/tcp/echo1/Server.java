package com.test.demo.http.bio.tcp.echo1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Server
 *
 * @author WangChao
 * @date 2020/05/05
 */
public class Server {

    public static void main(String[] args) {
        byte[] bs = new byte[1024];
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(9876));
            System.out.println("wait conn");
            // accept 阻塞
            Socket socket = serverSocket.accept();
            System.out.println("conn success");
            // read 阻塞
            System.out.println("wait data");
            socket.getInputStream().read(bs);
            System.out.println("data end");

            System.out.println(new String(bs));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (!serverSocket.isClosed()) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
