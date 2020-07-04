package com.tool.demo.reflect.example;

import com.tool.demo.reflect.example.vo.Person;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

public class Test {

    public static void main(String[] args) {
        Class<?> cls = Person.class; // 获取指定类的Class对象

        Package pack = cls.getPackage(); // 获取指定类的包定义
        System.out.println(pack.getName());  // 获取包名称 reflect.example.vo

        Class<?> parent = cls.getSuperclass();  // 获取父类的Class对象
        System.out.println(parent.getName());  // 获取父类包名称 reflect.example.abs.AbstractBase
        System.out.println(parent.getSuperclass().getName());  // java.lang.Object
        System.out.println(parent.getSuperclass().getSuperclass());  // null

        Class<?>[] interfaces = cls.getInterfaces();  // 获取实现接口的Class对象
        for (Class<?> clazz : interfaces) {
            // reflect.example.service.IChannelService
            // reflect.example.service.IMessageService
            System.out.println(clazz.getName());
        }

        System.out.println("-----------------------------");

        Constructor<?>[] declaredConstructors = cls.getDeclaredConstructors();  //公开申明的，本类的
        for (Constructor<?> constructor : declaredConstructors) {
            // public reflect.example.vo.Person()
            // public reflect.example.vo.Person(java.lang.String,int)
            System.out.println(constructor);
        }
        Constructor<?>[] constructors = cls.getConstructors();  //父子类的
        for (Constructor<?> constructor : constructors) {
            // public reflect.example.vo.Person()
            // public reflect.example.vo.Person(java.lang.String,int)
            System.out.println(constructor);
        }
        try {
            Constructor<?> constructor = cls.getConstructor(String.class, int.class);
            Object obj = constructor.newInstance("a", 18);  // 调用有参构造实例化
            System.out.println(obj);  // Person [name=a, age=18]
            //cls.newInstance();
            //cls.getDeclaredConstructor().newInstance();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        System.out.println("-----------------------------");

        Method[] methods = cls.getMethods();  // 获得全部方法(包括父类中的方法)
        for (Method method : methods) {
            System.out.println("**************");
            int modifiers = method.getModifiers();  // 修饰符
            System.out.println(modifiers);
            System.out.println(Modifier.toString(modifiers));

            Class<?> returnType = method.getReturnType();  // 返回类型
            System.out.println(returnType.getName());

            System.out.println(method.getName());  // 方法名

            Class<?>[] parameterTypes = method.getParameterTypes();  // 参数类型
            for (Class<?> pt : parameterTypes) {
                System.out.println(pt.getModifiers());
                System.out.println(pt.getName());
                System.out.println(pt.getSimpleName());
                System.out.println(pt.getTypeName());
            }

            Parameter[] parameters = method.getParameters();  // 参数
            for (Parameter parameter : parameters) {
                System.out.println(parameter.getName());
            }

            Class<?>[] exceptionTypes = method.getExceptionTypes();  //异常类型
            for (Class<?> exp : exceptionTypes) {
                System.out.println(exp.getName());
            }
            System.out.println("**************");
        }

        System.out.println("-----------------------------");

        try {
            Class<?> clazz = Class.forName("reflect.example.vo.Person");  // 获得指定Class对象
            Object obj = clazz.getDeclaredConstructor().newInstance();  // 无参构造实例化
            Method method = clazz.getMethod("setName", String.class); // 获得指定方法
            method.invoke(obj, "aa");  //执行方法

            Method declaredMethod = clazz.getDeclaredMethod("getName");
            System.out.println(declaredMethod.invoke(obj));

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }

        System.out.println("-----------------------------");
        Method[] declaredMethods = cls.getDeclaredMethods();  // 获得本类全部方法
        for (Method method : declaredMethods) {
            System.out.println(method.getName());
        }

        //cls.getDeclaredMethod(name, parameterTypes);  // 获得奔类指定方法

        System.out.println("----------------------------");

        Field[] fields = cls.getFields();  // 获取父类中公共成员
        for (Field field : fields) {
            System.out.println(field);
            System.out.println(field.getName());
        }

        System.out.println("**********");

        Field[] declaredFields = cls.getDeclaredFields();  // 获取本类中定义的成员
        for (Field field : declaredFields) {
            System.out.println(field);
        }

        System.out.println("**********");

        try {
            Class<?> cla = Class.forName("reflect.example.vo.Person");  // 获得指定Class对象
            Object obj = cla.getConstructor().newInstance();  // 无参构造实例化
            Field field = cla.getDeclaredField("name");  // 获取成员对象
            field.setAccessible(
                    true);  // 解除封装，不然报错java.lang.IllegalAccessException: Class reflect.example.Test can not access a member of class reflect.example.vo.Person with modifiers "private"
            field.set(obj, "xiaoming");
            System.out.println(field.get(obj));

            System.out.println(field.getType().getName());  // 获得完整的成员类型名称（同理包.类）
            System.out.println(field.getType().getSimpleName());  // 获得成员类型名称（同理类名称）
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }

}
