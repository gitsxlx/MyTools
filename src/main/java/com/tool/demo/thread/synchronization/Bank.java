package com.tool.demo.thread.synchronization;

/**
 * synchronized 方法
 * synchronized(this){} 代码块
 * volatile 属性
 *
 * @author WangChao
 * @date 2020/04/13
 */
public class Bank {

    //private volatile int count =0; //钱数
    private int count = 0; //钱数

    //加钱
    public void addMoney(int money) {
        synchronized (this) {
            count += money;
        }
        System.out.println(System.currentTimeMillis() + ":" + money);
    }

    //扣钱
    public synchronized void subMoney(int money) {
        synchronized (this) {
            if (count - money < 0) {
                System.out.println("金额不够");
                return;
            }
            count -= money;
        }
        System.out.println(System.currentTimeMillis() + ":" + money);
    }

    //查看钱数
    public void lookMoney() {
        System.out.println("当前金额:" + count);
    }

}
