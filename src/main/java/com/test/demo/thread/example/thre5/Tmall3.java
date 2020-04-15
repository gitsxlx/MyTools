package com.test.demo.thread.example.thre5;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Tmall3
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class Tmall3 implements Shop {

    private static final int MAX_COUNT = 10;

    private BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(MAX_COUNT);

    public void push() {
        //queue.add(1);
        try {
            queue.put(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void take() {
        //queue.remove();
        try {
            queue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void size() {
        while (true) {
            System.out.println("当前队列的长度为: " + queue.size());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
