package com.test.demo.http.bio.tcp.echo;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Socket服务端
 *
 * @author WangChao
 * @date 2020/04/14
 */
public class EchoServer {

    private static class ClientThread implements Runnable {

        private Socket client = null;  // 描述每一个不同的客户端
        private Scanner scan = null;
        private PrintStream out = null;
        private boolean flag = true;  // 循环标记

        public ClientThread(Socket client) throws Exception {
            this.client = client;
            // 首先需要先接受客户端发送来的消息，而后才可以将信息处理之后发送回客户端
            this.scan = new Scanner(client.getInputStream());  // 客户端输入流
            this.scan.useDelimiter("\n");  // 设置分隔符
            this.out = new PrintStream(client.getOutputStream());  // 客户端输出流
        }

        @Override
        public void run() {
            while (flag) {
                if (scan.hasNext()) {  // 现在有数据发送
                    String val = scan.next().trim();  // 接收发送的数据
                    if ("byebye".equalsIgnoreCase(val)) {
                        out.println("再见！");
                        this.flag = false;
                    } else {
                        System.out.println(Thread.currentThread().getName() + " 接收到的消息：" + val);
                        out.println("【ECHO】" + val);
                    }
                }
            }
            try {
                out.close();
                scan.close();
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(9999);  //设置服务器端口
        System.out.println("等待客户端连接......");
        boolean flag = true;  // 循环标记
        while (flag) {
            Socket client = server.accept();  //有客户端连接
            new Thread(new ClientThread(client)).start();
        }
        server.close();
    }

}
