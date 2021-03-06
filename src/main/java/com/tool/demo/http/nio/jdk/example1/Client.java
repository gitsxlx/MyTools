package com.tool.demo.http.nio.jdk.example1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * Client
 *
 * @author WangChao
 * @date 2020/05/05
 */
public class Client {

    public static void main(String[] args) {
        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress("127.0.0.1", 9091));
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String next = scanner.next();
                socket.getOutputStream().write(next.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (!socket.isClosed()) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
