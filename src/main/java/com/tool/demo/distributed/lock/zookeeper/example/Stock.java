package com.tool.demo.distributed.lock.zookeeper.example;

/**
 * 库存
 *
 * @author WangChao
 * @date 2020/06/21
 */
public class Stock {

    private static Integer COUNT = 1;

    public boolean reduceStock() {
        if (COUNT > 0) {
            COUNT--;
            return true;
        }
        return false;
    }

}
