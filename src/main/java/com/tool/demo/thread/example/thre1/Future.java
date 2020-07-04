package com.tool.demo.thread.example.thre1;

/**
 * Future
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class Future {

	private Product product;
	
	private boolean down;

	/**
	 * @return the product
	 */
	public synchronized Product getProduct() {
		while(!down) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public synchronized void setProduct(Product product) {
		if(down) {
			return;
		}
		this.product = product;
		this.down = true;
		notifyAll();
	}
	
}
