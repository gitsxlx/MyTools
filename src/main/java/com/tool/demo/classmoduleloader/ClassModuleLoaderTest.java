package com.tool.demo.classmoduleloader;

/**
 * 类中各模块加载顺序
 *
 * @author WangChao
 * @date 2020/04/10
 */
public class ClassModuleLoaderTest {

    //父子类中各模块加载的顺序：先父类的static成员变量 -> 子类的static成员变量 -> 父类的成员变量 -> 父类构造 -> 子类成员变量 -> 子类构造
    //这里静态代码块和静态成员变量没有先后之分，谁在上，谁就先初始化
    //类中各模块一般顺序：静态块（静态变量）——>成员变量——>构造方法(构造代码块优先于构造方法)——>静态方法
    //静态代码块优先于主方法执行

    ClassModuleLoaderTest demo = new ClassModuleLoaderTest();

    private static String A = "aaa";

    static {
        System.out.println("静态代码块");
    }

    {
        System.out.println("构造代码块");
    }

    public ClassModuleLoaderTest() {
        System.out.println("构造方法");
    }

    public static void main(String[] args) {
        System.out.println("主方法");
    }

}
