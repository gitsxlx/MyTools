package com.test.demo.thread.example.t2;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Demo5
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class Demo5 {

    public static void main(String[] args) {

        Timer timer = new Timer();

        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                // 实现定时任务
                System.out.println("timerTask run ...");

            }
        }, 0, 1000);

    }

}
