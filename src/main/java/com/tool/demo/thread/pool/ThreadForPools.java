package com.tool.demo.thread.pool;

/**
 * ThreadForPools
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class ThreadForPools implements Runnable {

    private Integer index;

    public ThreadForPools(Integer index) {
        this.index = index;
    }

    @Override
    public void run() {
        /***
         * 业务......省略
         */
        try {
            System.out.println(index + " 开始处理线程！！！");
            Thread.sleep(index * 100);
            System.out.println(index + " 我的线程标识是：" + this.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
