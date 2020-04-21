package com.test.demo.proxy.dynamicproxy;

/**
 * Main
 *
 * @author WangChao
 * @date 2020/04/10
 */
public class Main {

    public static void main(String[] args) {

        // 使用 JDK 生成的动态代理的前提是目标类必须有实现的接口

        // 如果某个类没有实现接口，就不能使用 JDK 动态代理。所以 CGLIB 代理就是解决这个问题的。
        // CGLIB 是以动态生成的子类继承目标的方式实现，在运行期动态的在内存中构建一个子类，如下：
        // CGLIB 使用的前提是目标类不能为 final 修饰。因为 final 修饰的类不能被继承。

        // 动态代理模式：动态代理类的源码是在程序运行期间，通过 JVM 反射等机制动态生成。代理类和委托类的关系是运行时才确定的。

        // 创建代理对象
        IUserDao target = new UserDao();
        System.out.println("目标对象：" + target.getClass());

        // 代理对象
        IUserDao proxy = (IUserDao) new ProxyFactory(target).getProxyInstance();
        System.out.println("代理对象：" + proxy.getClass());

        // 执行代理对象的方法
        proxy.save();

    }

}
