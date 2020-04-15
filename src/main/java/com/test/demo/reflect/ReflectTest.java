package com.test.demo.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/*
 * JAVA反射机制是在运行状态中，对于任意一个类，都能够知道这个类的所有属性和方法；对于任意一个对象，都能够调用它的任意一个方法和属性；这种动态获取的信息以及动态调用对象的方法的功能称为java语言的反射机制。
 *
 * 获取成员方法并调用：
 *
 * 1.批量的：
 * 		public Method[] getMethods():获取所有"公有方法"；（包含了父类的方法也包含Object类）
 * 		public Method[] getDeclaredMethods():获取所有的成员方法，包括私有的(不包括继承的)
 * 2.获取单个的：
 * 		public Method getMethod(String name,Class<?>... parameterTypes):
 * 					参数：
 * 						name : 方法名；
 * 						Class ... : 形参的Class类型对象
 * 		public Method getDeclaredMethod(String name,Class<?>... parameterTypes)
 *
 * 	 调用方法：
 * 		Method --> public Object invoke(Object obj,Object... args):
 * 					参数说明：
 * 					obj : 要调用方法的对象；
 * 					args:调用方式时所传递的实参；
 *      ):
 */

/**
 * reflect
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class ReflectTest {

    /**
     * 获取Class对象的三种方式
     * 1 Object ——> .getClass();
     * 2 任何数据类型（包括基本数据类型）都有一个“静态”的class属性, ——> .class
     * 3 通过Class类的静态方法：forName（String  className）(常用) ——> Class.forName("className")
     * 三种方式获取的Class对象是相同的
     */
    public static void main1(String[] args) {
        //第一种方式获取Class对象
        Student stu1 = new Student(); //这一new 产生一个Student对象，一个Class对象。
        Class<? extends Student> clazz = stu1.getClass(); //获取Class对象
        System.out.println(clazz.getName());

        //第二种方式获取Class对象
        Class<?> clazz2 = Student.class;
        System.out.println(clazz == clazz2);//判断第一种方式获取的Class对象和第二种方式获取的是否是同一个

        //第三种方式获取Class对象
        try {
            Class<?> clazz3 = Class.forName("tool.pojo.Student");//注意此字符串必须是真实路径，就是带包名的类路径，包名.类名
            System.out.println(clazz3 == clazz2);//判断三种方式是否获取的是同一个Class对象
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws Exception {
        //1.获取Class对象
        Class<?> clazz = Class.forName("tool.pojo.Student");
        clazz.newInstance();
        clazz.getDeclaredConstructor().newInstance();

        //2.获取构造方法
        System.out.println("**********************所有公有构造方法*********************************");
        Constructor[] conArray = clazz.getConstructors();
        for (Constructor c : conArray) {
            System.out.println(c);
        }

        System.out.println("************所有的构造方法(包括：私有、受保护、默认、公有)***************");
        conArray = clazz.getDeclaredConstructors();
        for (Constructor c : conArray) {
            System.out.println(c);
        }

        System.out.println("*****************获取公有、无参的构造方法*******************************");
        Constructor<?> con = clazz.getConstructor(null);
        //1>、因为是无参的构造方法所以类型是一个null,不写也可以：这里需要的是一个参数的类型，切记是类型
        //2>、返回的是描述这个无参构造函数的类对象。

        System.out.println("con = " + con);
        //调用构造方法
        Object obj = con.newInstance();
        //	System.out.println("obj = " + obj);
        //	Student stu = (Student)obj;

        System.out.println("******************获取私有构造方法，并调用*******************************");
        con = clazz.getDeclaredConstructor(char.class);
        System.out.println(con);
        //调用构造方法
        con.setAccessible(true);//暴力访问(忽略掉访问修饰符)
        obj = con.newInstance('男');

        //3.获取字段
        System.out.println("************获取所有公有的字段********************");
        Field[] fieldArray = clazz.getFields();
        for (Field f : fieldArray) {
            System.out.println(f);
        }
        System.out.println("************获取所有的字段(包括私有、受保护、默认的)********************");
        fieldArray = clazz.getDeclaredFields();
        for (Field f : fieldArray) {
            f.setAccessible(true);
            System.out.println(f);
            System.out.println(f.getName() + ":" + f.get(clazz.newInstance()));
        }
        System.out.println("*************获取公有字段**并调用***********************************");
        Field f = clazz.getField("name");
        System.out.println(f);
        //获取一个对象
        Object obje = clazz.getConstructor()
                .newInstance();//产生Student对象--》Student stu = new Student();
        //为字段设置值
        f.set(obje, "刘德华");//为Student对象中的name属性赋值--》stu.name = "刘德华"
        //验证
        Student stu = (Student) obje;
        System.out.println("验证姓名：" + stu.name);

        System.out.println("**************获取私有字段****并调用********************************");
        f = clazz.getDeclaredField("phoneNum");
        System.out.println(f);
        f.setAccessible(true);//暴力反射，解除私有限定
        f.set(obje, "18888889999");
        System.out.println("验证电话：" + stu);

        //4.获取所有公有方法
        System.out.println("***************获取所有的“公有”方法*******************");
        clazz.getMethods();
        Method[] methodArray = clazz.getMethods();
        for (Method m : methodArray) {
            System.out.println(m);
        }
        System.out.println("***************获取所有的方法，包括私有的*******************");
        methodArray = clazz.getDeclaredMethods();
        for (Method m : methodArray) {
            System.out.println(m);
        }
        System.out.println("***************获取公有的show1()方法*******************");
        Method m = clazz.getMethod("show1", String.class);
        System.out.println(m);
        //实例化一个Student对象
        Object object = clazz.getConstructor().newInstance();
        m.invoke(object, "刘德华");

        System.out.println("***************获取私有的show4()方法******************");
        m = clazz.getDeclaredMethod("show4", int.class);
        System.out.println(m);
        m.setAccessible(true);//解除私有限定
        Object result = m.invoke(object, 20);//需要两个参数，一个是要调用的对象（获取有反射），一个是实参
        System.out.println("返回值：" + result);

    }
}
