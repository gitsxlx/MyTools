package com.tool.demo.generics;

class Message<T> {

    private T content;

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

}

public class GenericsTest {

    public static void main(String[] args) {
        Message<String> msg = new Message<>();
        msg.setContent("aaa");
        fun(msg);
    }

    private static void fun(Message<String> msg) {
        System.out.println(msg.getContent());
    }

}
