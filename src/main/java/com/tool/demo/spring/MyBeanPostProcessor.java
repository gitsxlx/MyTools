package com.tool.demo.spring;

import java.lang.reflect.Field;
import java.util.Optional;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * MyBeanPostProcessor
 *
 * @author WangChao
 * @date 2020/04/18
 */
//@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

    public MyBeanPostProcessor() {
        System.out.println("MyBeanPostProcessor构造方法");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)
            throws BeansException {
        if (bean instanceof SpringTest) {
            try {
                SpringTest springTest = (SpringTest) bean;
                Field field = springTest.getClass().getDeclaredField("myBeanPostProcessor");
                field.setAccessible(true);
                System.out.println(
                        "MyBeanPostProcessor类postProcessBeforeInitialization方法前Autowired:"
                                + Optional.ofNullable(field.get(bean)).orElse(null));
                field.setAccessible(false);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        System.out.println("2、BeanPostProcessor.postProcessBeforeInitialization:" + beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException {
        System.out.println("6、BeanPostProcessor.postProcessAfterInitialization:" + beanName);
        return bean;
    }
}
