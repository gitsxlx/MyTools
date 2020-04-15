package com.test.demo.generics;

/**
 * 自定义泛型类
 *
 * 定义"模版"的时候，泛型用泛型字母：T 代替
 * 在使用的时候指定实际类型
 *
 * @author Administrator
 * @param <T>
 */
public class Student<T> {

    private T javase;

    //private static T javaee;   // 泛型不能使用在静态属性上

    public Student() {
    }

    public Student(T javase) {
        this();
        this.javase = javase;
    }

    public T getJavase() {
        return javase;
    }

    public void setJavase(T javase) {
        this.javase = javase;
    }

}

/**
 * 自定义泛型的使用
 * 在声明时指定具体的类型
 * 不能为基本类型
 * @author Administrator
 *
 */
class Demo02 {

    public static void main(String[] args) {
        //Student<int>  Student = new Student<int>(); //不能为基本类型，编译时异常

        Student<Integer> student = new Student<>();
        student.setJavase(85);
        System.out.println(student.getJavase());
    }
}
