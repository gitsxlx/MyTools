package com.test.demo.thread;

import java.io.IOException;
import java.util.Iterator;
import java.util.UUID;

/**
 * 线程测试
 *
 * @author WangChao
 * @date 2020/04/13
 */
public class ThreadTest {

    public static void main(String[] args) throws Exception {
	       /*String prefix = UUID.randomUUID().toString().replaceAll("-", "") + "::";
	       for (int i = 0; i < 1000; i++) {
	           final String value = prefix + i;
	           new Thread() {
	               @Override
	               public void run() {
	                   try {
	                       CloseableHttpClient httpClient = HttpClients.createDefault();
	                       HttpGet httpGet = new HttpGet("http://localhost:8080/test?key=" + value);
	                       httpClient.execute(httpGet);
	                       httpClient.close();
	                   } catch (IOException e) {
	                       e.printStackTrace();
	                   }
	               }
	           }.start();
	       }*/

        //利用Lambda实现多线程
        for (int x = 0; x < 3; x++) {
            String title = "线程对象-" + x;
		       /*Runnable run = () -> {
		    	   for (int y = 0; y < 10; y++) {
				       System.out.println(title + "运行.y =" + y);
		    	   }
		       };*/
            new Thread(() -> {
                for (int y = 0; y < 10; y++) {
                    System.out.println(title + "运行.y =" + y);
                }
            }).start();
        }
    }

}
