package com.tool.demo.thread;

/**
 * PingPong
 *
 * @author WangChao
 * @date 2020/04/13
 */
public class PingPong implements Runnable {

    synchronized void hit(long n) {
        for (int i = 1; i < 3; i++) {
            System.out.println(n + "-" + i + " ");
        }
    }

    public static void main(String[] args) {
        PingPong pp = new PingPong();
        //new Thread(new PingPong()).start();
        //new Thread(new PingPong()).start();
        new Thread(pp).start();
        new Thread(pp).start();
    }

    @Override
    public void run() {
        hit(Thread.currentThread().getId());
    }

}
