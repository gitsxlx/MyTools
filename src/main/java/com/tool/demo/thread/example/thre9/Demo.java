package com.tool.demo.thread.example.thre9;

import java.util.concurrent.atomic.DoubleAdder;
import java.util.concurrent.atomic.LongAdder;

/**
 * LongAdder
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class Demo {

    public static void main(String[] args) {

        LongAdder la = new LongAdder();
        la.increment();
        la.decrement();
        la.add(0);
        la.sum();
        la.intValue();
        la.longValue();

        DoubleAdder da = new DoubleAdder();

    }

}
