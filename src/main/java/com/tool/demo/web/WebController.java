package com.tool.demo.web;

import com.tool.demo.framework.log.Log;
import lombok.extern.slf4j.Slf4j;
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

    @Log("默认页")
    @GetMapping("/")
    public String index(){
        log.info("默认页 {}");
        return "welcome to MyTools";
    }

    @Log("字符串")
    @GetMapping("/{msg}")
    public String get(@PathVariable String msg){
        log.info("字符串 {}", msg);
        return msg;
    }

}
