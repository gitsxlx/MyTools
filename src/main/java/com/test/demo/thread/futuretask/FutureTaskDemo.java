package com.test.demo.thread.futuretask;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * FutureTask
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class FutureTaskDemo {

    public static void main(String[] args) throws Exception {
        Long start = System.currentTimeMillis();
//		m1();
//		m2();
        parlThread();
        Long end = System.currentTimeMillis();

        System.out.println(end - start);
    }

    public static void parlThread() throws Exception {
        Callable<String> c1 = new Callable<String>() {

            @Override
            public String call() throws Exception {

                return m1();
            }
        };

        Callable<String> c2 = new Callable<String>() {

            @Override
            public String call() throws Exception {
                return m2();
            }
        };

        FutureTask<String> f1 = new FutureTask<String>(c1);
        FutureTask<String> f2 = new FutureTask<String>(c2);

        new Thread(f1).start();
        new Thread(f2).start();

        System.out.println(f1.get());
        System.out.println(f2.get());

    }

    public static String m1() throws Exception {
        TimeUnit.MILLISECONDS.sleep(1500);
        return "m1";
    }

    public static String m2() throws Exception {
        TimeUnit.MILLISECONDS.sleep(1500);
        return "m2";
    }

}
