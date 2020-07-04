package com.tool.demo.thread.synchronization;

/**
 * SyncThreadTest
 *
 * @author WangChao
 * @date 2020/04/13
 */
public class SyncThreadTest {

    public static void main(String args[]) {
        //final Bank bank=new Bank();
        //final Bank1 bank = new Bank1();
        final Bank2 bank=new Bank2();

        Thread tadd = new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    bank.addMoney(100);
                    bank.lookMoney();
                    System.out.println("\n");

                }
            }
        });

        Thread tsub = new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    bank.subMoney(100);
                    bank.lookMoney();
                    System.out.println("\n");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        tsub.start();

        tadd.start();
    }

}
