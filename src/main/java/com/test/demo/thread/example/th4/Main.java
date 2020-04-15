package com.test.demo.thread.example.th4;

/**
 * Main
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class Main {

    public static void main(String[] args) {

        Demo d = new Demo();

        //写和写操作
		/*new Thread(new Runnable() {
			@Override
			public void run() {
				d.put("key1", "value1");
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				d.put("key2", "value2");
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				d.put("key3", "value3");
			}
		}).start();*/

        //写和读操作
		/*new Thread(new Runnable() {
			@Override
			public void run() {
				d.put("key1", "value1");
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(d.get("key1"));
			}
		}).start();*/

        //读和读操作，效率高
        d.put("key1", "value1");

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(d.get("key1"));
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(d.get("key1"));
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(d.get("key1"));
            }
        }).start();

    }

}
