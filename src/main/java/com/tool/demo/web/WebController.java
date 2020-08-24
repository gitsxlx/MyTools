package com.tool.demo.web;

import com.tool.demo.framework.log.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * WebController
 *
 * @author WangChao
 * @date 2020/07/04
 */
@Slf4j
@RestController
@RequestMapping
public class WebController {

    @Value("${spring.application.name:YuLinYun}")
    private String applicationName;

    @Value("${server.servlet.context-path:YuLinYun}")
    private static String contextPath;

    private static String contextPath1;

    @Value("${server.servlet.context-path:YuLinYun}")
    private static String contextPath2;

    /**
     * 注意：
     * spring不允许/不支持把值注入到静态变量中；
     * spring支持set方法注入，可以利用非静态setter方法注入静态变量；
     *
     * 原因：
     * 1、静态变量(即类变量)是一个类的属性，而不是对象的属性。spring依赖注入是基于对象层面上的。
     * 2、而且使用静态变量扩大了静态方法的使用范围，静态方法在spring是不推荐使用的，依赖注入的主要目的是让容器去产生一个对象的实例，然后在整个生命周期中使用他们。
     * 一旦你使用静态方法，就不再需要去产生这个类的实例，这会让testing变得更加困难，同时你也不能为一个给定的类，依靠注入方式去产生多个具有不同的依赖环境的实例。
     * 这种static field是隐含共享的，并且是一种global全局状态，spring同样不推荐这样去做。
     *
     * 源码：
     * @see AutowiredAnnotationBeanPostProcessor#buildAutowiringMetadata 方法获取到需要注入的属性
     * @see AutowiredAnnotationBeanPostProcessor#inject 方法获取属性值、注入
     *
     * @param contextPath1
     */
    @Value("${server.servlet.context-path:YuLinYun}")
    public void setContextPath1(String contextPath1) {
        WebController.contextPath1 = contextPath1;
    }

    public void setContextPath2(String contextPath2) {
        WebController.contextPath2 = contextPath2;
    }

    @Log("默认页")
    @GetMapping("/")
    public String index() {
        //默认页, applicationName:MyTools, contextPath:null, contextPath1:/tools, contextPath2:null
        log.info("默认页, applicationName:{}, contextPath:{}, contextPath1:{}, contextPath2:{}",
                applicationName, contextPath, contextPath1, contextPath2);
        return "welcome to MyTools";
    }

    @Log("字符串")
    @GetMapping("/{msg}")
    public String get(@PathVariable String msg){
        log.info("字符串 {}", msg);
        return msg;
    }

}
