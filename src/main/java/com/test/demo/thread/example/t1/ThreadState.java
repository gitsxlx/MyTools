package com.test.demo.thread.example.t1;

/**
 * ThreadState
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class ThreadState {

    public static void main(String[] args) {
        //线程的六种状态
        System.out.println("======线程的六种状态======");
        System.out.println("线程-初始状态：" + Thread.State.NEW);
        System.out.println("线程-就绪状态：" + Thread.State.RUNNABLE);
        System.out.println("线程-阻塞状态：" + Thread.State.BLOCKED);
        System.out.println("线程-等待状态：" + Thread.State.WAITING);
        System.out.println("线程-限时等待状态：" + Thread.State.TIMED_WAITING);
        System.out.println("线程-终止状态：" + Thread.State.TERMINATED);
    }


}
