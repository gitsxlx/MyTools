package com.test.demo.reflect;

import java.lang.reflect.Field;
import lombok.Data;

/**
 * @program: callcenter-openapi
 * @description: 反射工具
 * @author: Mr.Wang
 * @create: 2018-12-20 11:29
 **/
public class ReflectHelper {

    /**
     * 获取obj对象fieldName的Field
     * @param obj
     * @param fieldName
     * @return
     */
    public static <T> Field getFieldByFieldName(T obj, String fieldName) {
        for (Class<?> superClass = obj.getClass(); superClass != Object.class;
                superClass = superClass.getSuperclass()) {
            try {
                return superClass.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
            }
        }
        return null;
    }

    /**
     * 获取obj对象fieldName的属性值
     * @param obj
     * @param fieldName
     * @return
     * @throws SecurityException
     * @throws NoSuchFieldException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */  //getFieldValue
    public static <T> Object getValueByFieldName(T obj, String fieldName)
            throws SecurityException, NoSuchFieldException,
            IllegalArgumentException, IllegalAccessException {
        Field field = getFieldByFieldName(obj, fieldName);
        Object value = null;
        if (field != null) {
            if (field.isAccessible()) {
                value = field.get(obj);
            } else {
                field.setAccessible(true);
                value = field.get(obj);
                field.setAccessible(false);
            }
        }
        return value;
    }

    /**
     * 设置obj对象fieldName的属性值
     * @param obj
     * @param fieldName
     * @param value
     * @throws SecurityException
     * @throws NoSuchFieldException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */  //setFieldValue
    public static <T> void setValueByFieldName(T obj, String fieldName,
            Object value) throws SecurityException, NoSuchFieldException,
            IllegalArgumentException, IllegalAccessException {
        Field field = obj.getClass().getDeclaredField(fieldName);
        if (field.isAccessible()) {
            field.set(obj, value);
        } else {
            field.setAccessible(true);
            field.set(obj, value);
            field.setAccessible(false);
        }
    }

    /**
     * 测试主函数
     * @param args
     */
    public static void main(String[] args) {
        try {
            Class cls = Class.forName("com.emotte.callcenteropenapi.customer.model.Customer");
            Field field;
            try {
                // 得到一个类的实例
                var customer = cls.newInstance();
                // 调用根据字段名得到字段的方法
                field = ReflectHelper.getFieldByFieldName(customer, "name");
                System.out.println(field.getName());

                // 根据字段名给字段赋值
                ReflectHelper.setValueByFieldName(customer, "name", "admin");

                // 根据字段名获取到字段值
                Object nameValue = ReflectHelper.getValueByFieldName(customer, "name");
                System.out.println(nameValue);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @SuppressWarnings("unchecked")
    public static <T> T getInstance(String className, T clazz) {
        T instance = null;
        try {
            instance = (T) Class.forName(className).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }

}

@Data
class Customer {

    private String name;
}
