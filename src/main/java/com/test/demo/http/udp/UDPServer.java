package com.test.demo.http.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * UDP服务端
 *
 * @author WangChao
 * @date 2020/04/14
 */
public class UDPServer {

    public static void main(String[] args) throws Exception {
        DatagramSocket server = new DatagramSocket(9000);
        String str = "www.95081.com";  // 要发送的消息内容
        DatagramPacket packet = new DatagramPacket(str.getBytes(), 0, str.length(),
                InetAddress.getByName("localhost"),
                9999);  // 接收数据
        server.send(packet);  // 发送消息
        System.out.println("消息发送完毕......");
        server.close();
    }

}
