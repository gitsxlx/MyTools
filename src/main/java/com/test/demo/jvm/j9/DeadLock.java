package com.test.demo.jvm.j9;

/**
 * 死锁
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class DeadLock implements Runnable {
	
	private Object obj1;
	private Object obj2;
	
	public DeadLock(Object obj1, Object obj2) {
		this.obj1 = obj1;
		this.obj2 = obj2;
	}

	@Override
	public void run() {
		synchronized (obj1) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (obj2) {
				System.out.println("hello");
			}
		}
	}

}
