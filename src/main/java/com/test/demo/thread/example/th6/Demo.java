package com.test.demo.thread.example.th6;

/**
 * java.lang.IllegalMonitorStateException
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class Demo {

    private volatile int signal;

    public int getSignal() {
        return signal;
    }

    public void setSignal(int signal) {
        this.signal = signal;
    }

    public static void main(String[] args) {

        Demo d = new Demo();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("修改状态的线程执行...");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                d.setSignal(1);
                System.out.println("状态值修改成功...");
                notify();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                // 等待signal为1开始执行，否则不能执行
                while (d.getSignal() != 1) {
					/*try {
						Thread.sleep(1500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}*/
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // 当信号为1的时候，执行代码
                System.out.println("模拟的代码执行");
            }
        }).start();

    }

}
