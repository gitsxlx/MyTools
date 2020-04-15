package com.test.demo.thread.example.t2;

/**
 * Demo3
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class Demo3 {

    public static void main(String[] args) {

        //方式一
		/*new Thread() {
			public void run() {
				System.out.println("thread start ...");
			}
		}.start();*/

        //方式二
		/*new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("thread start ...");				
			}
		}).start();*/

        //方式三
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("runnable");
            }
        }) {
            //执行子类的方法，子类重写父类run方法
            @Override
            public void run() {
                System.out.println("sub");
            }

            ;
        }.start();

    }

}
