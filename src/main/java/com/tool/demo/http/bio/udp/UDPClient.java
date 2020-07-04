package com.tool.demo.http.bio.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * UDP客户端
 *
 * @author WangChao
 * @date 2020/04/14
 */
public class UDPClient {

    public static void main(String[] args) throws Exception {
        DatagramSocket client = new DatagramSocket(9999);
        byte[] data = new byte[1024];
        DatagramPacket packet = new DatagramPacket(data, data.length);  // 接收数据
        System.out.println("客户端等待接收发送的消息......");
        client.receive(packet);  // 接收消息
        System.out.println("接收到的消息内容为：" + new String(data, 0, packet.getLength()));
        client.close();
    }
}
