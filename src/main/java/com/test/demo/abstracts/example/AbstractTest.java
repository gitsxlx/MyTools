package com.test.demo.abstracts.example;

/**
 * AbstractTest
 *
 * @author WangChao
 * @date 2020/04/06
 */
public class AbstractTest {

    public static void main(String[] args) {
        //抽象类不能实例化，不能new
        //Message msg = new Message();  //Cannot instantiate the type Message

        Message msg = new DatabaseMessage();
        msg.setType("客户信息");
        System.out.println(msg.getConnectInfo());
        System.out.println(msg.getType());

        Message msg1 = Message.getInstance();
        System.out.println(msg1.getConnectInfo());
    }

}

abstract class Message {

    private String type;

    public Message() {
    }

    /**
     * 获得连接信息
     * @return
     */
    public abstract String getConnectInfo();

    public static Message getInstance() {
        return new DatabaseMessage();
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}

class DatabaseMessage extends Message {

    @Override
    public String getConnectInfo() {
        return "Mysql数据库连接信息。";
    }

}