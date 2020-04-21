package com.test.demo.abstracts.test;

/**
 * TestAbstract1
 *
 * @author WangChao
 * @date 2020/04/20
 */
public abstract class TestAbstract1 extends TestAbstract implements TestInterfaceChild,
        TestInterface {

    protected int aa = 10;

    /**
     * aa
     * @return
     */
    protected abstract int aa();

    /**
     * 2-1、静态方法不能是抽象的
     * @return
     */
    public static String bb() {
        return "bb";
    }

    /**
     * 2-2、构造方法不能是抽象的
     */
    public TestAbstract1() {
        super();
    }

    public String cc() {
        return "cc";
    }

}
