package com.tool.demo.thread.example.t7;

/**
 * 保证可见性的前提：多个线程拿到的是同一把锁，否则是保证不了的
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class Demo {

    public volatile int a = 1;

    public synchronized int getA() {
        return a;
    }

    public synchronized void setA(int a) {
        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.a = a;
    }

    public static void main(String[] args) {

        Demo d = new Demo();

        d.a = 10;

		/*new Thread(new Runnable() {
			@Override
			public void run() {
				d.setA(10);
			}
		}).start();*/

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(d.getA());
            }
        }).start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("最终的结果 " + d.getA());

    }

}
