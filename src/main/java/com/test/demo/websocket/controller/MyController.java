package com.test.demo.websocket.controller;

import com.test.demo.websocket.service.WebSocketServer;
import java.io.IOException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * MyController
 *
 * 访问 http://localhost:8081/resources/websocket.html
 *
 * @author WangChao
 * @date 2020/05/06
 */
@Controller
public class MyController {

    //页面请求
    @GetMapping("/socket/{cid}")
    public ModelAndView socket(@PathVariable String cid) {
        ModelAndView mav = new ModelAndView("/socket");
        mav.addObject("cid", cid);
        return mav;
    }

    //推送数据接口
    @ResponseBody
    @RequestMapping("/socket/push/{cid}")
    public String pushToWeb(@PathVariable String cid, String message) {
        try {
            WebSocketServer.sendInfo(message, cid);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "发送成功";
    }
}
