package com.tool.demo.microservices;

import java.io.File;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.http.HttpServlet;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatWebServer;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.RequestHandledEvent;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.apache.commons.logging.LogAdapter;

/**
 * TomcatTest
 *
 * 模拟原生tomcat
 * @see WebMvcConfigurer
 * @see WebApplicationInitializer
 * @see HttpServlet
 *
 * 模拟springboot中tomcat
 * @see ServletContainerInitializer
 *
 * 内置tomcat
 * @see Tomcat
 * @see TomcatWebServer
 *
 * 日志
 * @see LogFactory
 * LogAdapter
 *
 * websocket通信
 * @see SimpMessagingTemplate
 *
 * 上传文件
 * Multipart Resolver
 *  Apache Commons FileUpload
 *  Servlet 3.0 MultipartFile
 *
 * 事件
 * 应用程序上下文相关联
 * @see ApplicationContextEvent
 *  @see ContextStartedEvent
 *  @see ContextRefreshedEvent
 *  @see ContextClosedEvent
 *  @see ContextStoppedEvent
 * 与request请求相关联
 * @see RequestHandledEvent
 * 事件广播器
 * @see ApplicationEventMulticaster
 *
 * JAVA 中已经有了对观察者模式的支持类
 * 	java.util.Observable 类 （jdk9设置为Deprecated）
 * 	java.util.Observer 接口 （jdk9设置为Deprecated）
 *
 * 注入 先按type，再按name
 * @see AutowiredAnnotationBeanPostProcessor
 * @see DefaultListableBeanFactory findAutowireCandidates
 *
 * @author WangChao
 * @date 2020/06/06
 */
public class TomcatTest {

    public static void main(String[] args) {
        TomcatTest.run();
    }

    public static void run() {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(9080);
        String sourcePath = TomcatTest.class.getResource("/").getPath();
        //告诉tomcat你的源码在哪里
        Context ctx = tomcat.addWebapp("/", new File("src/main").getAbsolutePath());
        WebResourceRoot resources = new StandardRoot(ctx);
        resources.addPreResources(
                new DirResourceSet(resources, "/target/classes", sourcePath, "/"));
        ctx.setResources(resources);
        try {
            tomcat.start();
            tomcat.getServer().await();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }
    }

}
