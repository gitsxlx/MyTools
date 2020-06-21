package com.test.demo.distributed.config.zookeeper;


import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author WangChao
 * @date 2020/06/21
 */
public class Main {

    public static void main(String[] args) {
        Config config = new Config();
        config.save("timeout", "100");

        for (int i = 0; i < 100; i++) {
            System.out.println(config.get("timeout"));
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
