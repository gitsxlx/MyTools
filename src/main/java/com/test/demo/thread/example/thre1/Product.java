package com.test.demo.thread.example.thre1;

/**
 * Product
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class Product {

	private int id;
	private String name;
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + "]";
	}
	/**
	 * @param id
	 * @param name
	 */
	public Product(int id, String name) {
		System.out.println("开始生产" + name);
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.id = id;
		this.name = name;
		System.out.println(name + "生产完毕");
	}
	
}
