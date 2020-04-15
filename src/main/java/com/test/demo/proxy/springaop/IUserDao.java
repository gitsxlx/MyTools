package com.test.demo.proxy.springaop;

import org.aspectj.lang.ProceedingJoinPoint;

public interface IUserDao {

    void save();
}

// 用于测试 CGLIB 动态代理
class OrderDao {

    public void save() {
        //int i =1/0; 用于测试异常通知
        System.out.println("保存订单...");
    }
}

//用于测试 JDK 动态代理
class UserDao implements IUserDao {

    @Override
    public void save() {
        //int i =1/0; 用于测试异常通知
        System.out.println("保存用户...");
    }
}

//切面类
class TransactionAop {

    public void beginTransaction() {
        System.out.println("[前置通知]  开启事务..");
    }

    public void commit() {
        System.out.println("[后置通知] 提交事务..");
    }

    public void afterReturing() {
        System.out.println("[返回后通知]");
    }

    public void afterThrowing() {
        System.out.println("[异常通知]");
    }

    public void arroud(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("[环绕前：]");
        pjp.proceed(); // 执行目标方法
        System.out.println("[环绕后：]");
    }
}
