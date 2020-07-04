package com.tool.demo.abstracts.test;

/**
 * TestInterfaceChild
 *
 * @author WangChao
 * @date 2020/04/20
 */
public interface TestInterfaceChild extends TestInterface1, TestInterface2 {

    //Interfaces cannot have constructors
	/*public TestInterface() {
		
	}*/

    public int oo = 0;

    /**
     * face
     */
    void face();

}
