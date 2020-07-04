package com.tool.demo.abstracts.test;

/**
 * TestAbstractChild
 *
 * @author WangChao
 * @date 2020/04/20
 */
public class TestAbstractChild extends TestAbstract1 {

    //3、父类的抽象方法在子类中实现
    //当一个方法为抽象方法时，意味着这个方法必须被子类的方法所重写，否则其子类的该方法仍然是abstract的，而这个子类也必须是抽象的，即声明为abstract
    @Override
    protected int aa() {
        return super.aa;
    }

    @Override
    public void draw() {
        System.out.println("draw");
    }

    @Override
    public void fill() {
        System.out.println("fill");
    }

    @Override
    void abstract1() {
        System.out.println("abstract1");
    }

    @Override
    public void face() {
        System.out.println("face");
    }

    @Override
    public void face1() {
        System.out.println("face1");
    }

}
