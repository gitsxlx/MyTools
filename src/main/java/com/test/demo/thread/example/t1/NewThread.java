package com.test.demo.thread.example.t1;

/**
 * NewThread
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class NewThread implements Runnable {

    @Override
    public synchronized void run() {
        while (true) {
            try {
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("自定义线程执行了...");
			/*try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
        }

    }

    public static void main(String[] args) {

        NewThread n = new NewThread();

        Thread thread = new Thread(n);  //创建线程，并指定线程任务

        thread.start();  //启动线程

        while (true) {
            synchronized (n) {
                System.out.println("主线程执行了...");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                n.notifyAll();
            }
        }

    }

}
