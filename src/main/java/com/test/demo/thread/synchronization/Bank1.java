package com.test.demo.thread.synchronization;

/**
 * ThreadLocal
 *
 * @author WangChao
 * @date 2020/04/13
 */
public class Bank1 {

    private static ThreadLocal<Integer> count = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    // 加钱
    public void addMoney(int money) {
        count.set(count.get() + money);
        System.out.println(System.currentTimeMillis() + ":" + money);
    }

    // 减钱
    public void subMoney(int money) {
        if (count.get() - money < 0) {
            System.out.println("金额不够");
            return;
        }
        count.set(count.get() - money);
        System.out.println(System.currentTimeMillis() + ":" + money);
    }

    // 查看钱数
    public void lookMoney() {
        System.out.println("当前金额:" + count.get());
    }

}
