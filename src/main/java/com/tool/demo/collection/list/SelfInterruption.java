package com.tool.demo.collection.list;

/**
 * SelfInterruption
 *
 * @author WangChao
 * @date 2020/04/20
 */
public class SelfInterruption {

    public static void main(String[] args) {
        Thread.currentThread().interrupt();
        if (Thread.interrupted()) {
            System.out.println("Interrupted:" + Thread.interrupted());
        } else {
            System.out.println("Not interrupted:" + Thread.interrupted());
        }
    }

}
