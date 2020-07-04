package com.tool.demo.spring;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Spring生命周期初始化及销毁方法执行顺序测试
 *
 * @author WangChao
 * @date 2020/04/18
 */
//@Component
//@ComponentScan("com.test.demo.spring")
public class SpringTest implements InitializingBean, DisposableBean {

    @Autowired
    private MyBeanPostProcessor myBeanPostProcessor;

    public static void main(String[] args) {
        System.out.println("main...");

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        //AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.test.demo.spring");
        context.close();

        System.out.println("main close...");
    }

    public SpringTest() {
        System.out.println("SpringTest类构造方法前Autowired:" + myBeanPostProcessor);
        System.out.println("1、SpringTest 构造方法...");
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("3、PostConstruct...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("4、InitializingBean.afterPropertiesSet...");
    }

    /*@Bean(initMethod = "initMethod", destroyMethod = "destroyMethod")
    BeanTest beanTest() {
        return new BeanTest();
    }*/

    public void initMethod() {
        System.out.println("5、this is init method...");
    }

    /*-----------------------------------------*/

    @PreDestroy
    public void preDestroy() {
        System.out.println("10、PreDestroy...");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("11、DisposableBean.destroy...");
    }

    public void destroyMethod() {
        System.out.println("12、this is destroy method...");
    }

}

