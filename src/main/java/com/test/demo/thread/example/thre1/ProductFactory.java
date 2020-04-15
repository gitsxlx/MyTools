package com.test.demo.thread.example.thre1;

import java.util.Random;

/**
 * ProductFactory
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class ProductFactory {
	
	public Future createProduct(String name) {
		Future f = new Future();  // 创建一个订单
		System.out.println("下单成功，你可以去上班了...");
		// 生产产品
		new Thread(new Runnable() {
			@Override
			public void run() {
				Product p = new Product(new Random().nextInt(), name);
				f.setProduct(p);
			}
		}).start();
		
		return f;
	}

}
