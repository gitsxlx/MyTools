package com.tool.demo.thread.example.t2;

/**
 * Demo1
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class Demo1 extends Thread {

    public Demo1() {
    }

    public Demo1(String name) {
        super(name);
    }

    @Override
    public void run() {
        while (!interrupted()) {  //
            System.out.println(getName() + "线程执行了...");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {

        Demo1 d1 = new Demo1("first-thread");
        Demo1 d2 = new Demo1();
        d2.setName("second-thread");

        //d1.setDaemon(true);  //守护线程，跟随主线程一起结束
        //d2.setDaemon(true);

        d1.start();
        d2.start();

        d1.interrupt();  //中断线程

        //d1.stop();  //使用这种方式，线程获取的锁、资源没有释放，让线程处于无限期等待状态
		
		/*try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

    }

}
