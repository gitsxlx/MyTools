package com.tool.demo.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义方法注解
 *
 * @author WangChao
 * @date 2020/04/11
 */
@Documented  //仅仅作为一个标记注解，说明该注解类应该被javadoc工具记录(默认情况下是不记录的)，不在代码运行时产生影响
@Retention(RetentionPolicy.RUNTIME)  //定义注解保留的环境
@Target(ElementType.METHOD)  //规定注解使用的位置，类、方法、接口等
@Inherited  //该注解会被子类继承，注意，仅针对类，成员属性、方法并不受此注释的影响
public @interface MethodInfo {

    String author() default "Mr.Wang";

    String date();

    int revision() default 1;

    String comments();

}