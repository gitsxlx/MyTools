package com.test.demo.proxy.staticproxy;

/**
 * Main
 *
 * @author WangChao
 * @date 2020/04/10
 */
public class Main {

    public static void main(String[] args) {

        // 为什么会有面向切面编程（AOP）？我们知道Java是一个面向对象（OOP）的语言，但它有一些弊端，比如当我们需要为多个不具有继承关系的对象引入一个公共行为，例如日志、权限验证、事务等功能时，只能在在每个对象里引用公共行为。这样做不便于维护，而且有大量重复代码。AOP的出现弥补了OOP的这点不足。

        // 代理模式：为其他对象提供一种代理以控制对这个对象的访问。这段话比较官方，但我更倾向于用自己的语言理解：比如A对象要做一件事情，在没有代理前，自己来做；在对 A 代理后，由 A 的代理类 B 来做。代理其实是在原实例前后加了一层处理，这也是 AOP 的初级轮廓。

        // 静态代理模式：静态代理说白了，就是在程序运行前就已经存在代理类的字节码文件、代理类和原始类的关系在运行前就已经确定。

        // 代理对象
        IUserDao proxy = new UserDaoProxy();
        // 执行代理方法
        proxy.save();
    }

}
