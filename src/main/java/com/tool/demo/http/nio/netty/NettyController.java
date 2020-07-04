package com.tool.demo.http.nio.netty;

import java.util.UUID;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * NettyController
 * 
 * 开启NettyChatServer中@PostConstruct，启动项目
 * 访问：http://127.0.0.1:8080/tools/netty/index
 *
 * @author WangChao
 * @date 2020/05/15
 */
@Controller
@RequestMapping("/netty")
public class NettyController {

    @RequestMapping("/index")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("netty");
        mav.addObject("uid", UUID.randomUUID());
        return mav;
    }

}
