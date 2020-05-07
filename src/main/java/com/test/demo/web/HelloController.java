package com.test.demo.web;

import com.test.demo.framework.log.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * HelloController
 *
 * @author WangChao
 * @date 2020/04/27
 */
@Slf4j
@RestController
@RequestMapping("/hello")
public class HelloController {

    @Log("根据id获取信息")
    @GetMapping("/get/{id}")
    public String get(@PathVariable Long id){
        log.info("获取数据 {}", id);
        return id + "";
    }

    @Log("添加信息")
    @PostMapping("/add")
    @ResponseBody
    public String add(String name){
        log.info("添加数据 {}", name);
        return name;
    }

}
