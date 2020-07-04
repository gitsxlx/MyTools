package com.tool.demo.thread.example.th6;

/**
 * wait和notify必须用在synchronized代码块中，同时使用当前锁对象的wait和notify方法
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class Demo2 {

    private volatile int signal;

    public int getSignal() {
        return signal;
    }

    public void setSignal(int signal) {
        this.signal = signal;
    }

    public static void main(String[] args) {

        Demo2 d = new Demo2();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (d) {
                    System.out.println("修改状态的线程执行...");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    d.setSignal(1);
                    System.out.println("状态值修改成功...");
                    d.notify();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (d) {
                    // 等待signal为1开始执行，否则不能执行
                    while (d.getSignal() != 1) {
						/*try {
							Thread.sleep(1500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}*/
                        try {
                            d.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    // 当信号为1的时候，执行代码
                    System.out.println("模拟的代码执行");
                }
            }
        }).start();

    }

}
