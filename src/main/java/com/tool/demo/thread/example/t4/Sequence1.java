package com.tool.demo.thread.example.t4;

/**
 * Sequence1
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class Sequence1 {

    //private static int value;
    private int value;

    //synchronized 放在普通方法上，内置锁就是当前类的实例
    public synchronized int getNext() {
        return value++;
    }

    //synchronized 修饰静态方法，内置锁就是当前的Class字节码对象
    //Sequence.class
    public static synchronized int getPrevious() {
        //return value--;
        return 0;
    }

    //代码块
    public int xx() {
        synchronized (Sequence1.class) {
            if (value > 0) {
                return value;
            } else {
                return -1;
            }
        }
    }

    public static void main(String[] args) {

        Sequence1 s = new Sequence1();
		
		/*while(true) {
			System.out.println(s.getNext());
		}*/

        new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    System.out.println(Thread.currentThread().getName() + " " + s.getNext());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    System.out.println(Thread.currentThread().getName() + " " + s.getNext());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    System.out.println(Thread.currentThread().getName() + " " + s.getNext());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }

}
