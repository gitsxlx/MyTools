package com.tool.demo.http.bio.tcp.echo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Socket客户端
 *
 * @author WangChao
 * @date 2020/04/14
 */
public class EchoClient {

    private static final BufferedReader KEYBOARD_INPUT = new BufferedReader(
            new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {
        Socket client = new Socket("localhost", 9999);  // 定义服务端的连接信息
        // 现在的客户端需要有输入和输出的操作支持，所以依然要准备出Scanner与PrintWriter
        Scanner scan = new Scanner(client.getInputStream());  // 接收服务器端的输入内容
        scan.useDelimiter("\n");
        PrintStream out = new PrintStream(client.getOutputStream());  // 向服务器端发送内容
        boolean flag = true;
        while (flag) {
            String input = getString("请输入要发送的内容：").trim();
            out.println(input);  // 加换行
            if (scan.hasNext()) {  // 服务器端有回应了
                String msg = scan.next();
                System.out.println(msg);
            }
            if ("byebye".equalsIgnoreCase(input)) {
                flag = false;
            }
        }
        out.close();
        scan.close();
        client.close();
    }

    public static String getString(String prompt) throws Exception {
        System.out.println(prompt);
        String str = KEYBOARD_INPUT.readLine();
        return str;
    }

}
