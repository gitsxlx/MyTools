package com.test.demo.interview;

/**
 * Employee
 *
 * @author WangChao
 * @date 2020/04/10
 */
public class Employee {
	
	private Integer id;
	
	private String name;
	
	private Double salary;
	
	public Employee() {
	
	}
	
	/**
	 * @param name
	 * @param salary
	 */
	public Employee(String name, Double salary) {
		super();
		this.name = name;
		this.salary = salary;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
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

	/**
	 * @return the salary
	 */
	public Double getSalary() {
		return salary;
	}

	/**
	 * @param salary the salary to set
	 */
	public void setSalary(Double salary) {
		this.salary = salary;
	}

}
