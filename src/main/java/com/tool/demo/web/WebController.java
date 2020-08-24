package com.tool.demo.web;

import com.tool.demo.framework.log.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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

    /**
     * 注意：
     * spring不允许/不支持把值注入到静态变量中；
     * spring支持set方法注入，可以利用非静态setter方法注入静态变量；
     *
     * @param contextPath1
     */
    @Value("${server.servlet.context-path:YuLinYun}")
    public void setContextPath1(String contextPath1) {
        WebController.contextPath1 = contextPath1;
    }

    @Log("默认页")
    @GetMapping("/")
    public String index() {
        log.info("默认页, applicationName:{}, contextPath:{}, contextPath1:{}", applicationName,
                contextPath, contextPath1);
        return "welcome to MyTools";
    }

    @Log("字符串")
    @GetMapping("/{msg}")
    public String get(@PathVariable String msg){
        log.info("字符串 {}", msg);
        return msg;
    }

}
