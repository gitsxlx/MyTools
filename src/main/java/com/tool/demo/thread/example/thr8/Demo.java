package com.tool.demo.thread.example.thr8;

import java.util.concurrent.Exchanger;

/**
 * Exchanger
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class Demo {

    public void a(Exchanger<String> exch) {

        System.out.println("a 方法执行...");

        try {
            System.out.println("a 线程正在抓取数据...");
            Thread.sleep(2000);
            System.out.println("a 线程抓取到数据...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String res = "12345";

        try {
            System.out.println("a 等待对比结果...");
            exch.exchange(res);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void b(Exchanger<String> exch) {

        System.out.println("b 方法执行...");

        try {
            System.out.println("b 线程正在抓取数据...");
            Thread.sleep(4000);
            System.out.println("b 线程抓取到数据...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String res = "12345";

        try {
            String value = exch.exchange(res);
            System.out.println("开始进行对比...");
            System.out.println("对比结果为：" + value.equals(res));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

        Demo d = new Demo();
        Exchanger<String> exch = new Exchanger<>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                d.a(exch);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                d.b(exch);
            }
        }).start();

    }

}
