package com.tool.demo.timer;

import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 定时任务
 * 1、quartz 推荐
 * 2、@Scheduled注解
 * 3、Timer
 * 4、线程
 *
 * @author WangChao
 * @date 2020/04/07
 */
@Slf4j
@Component
public class TimerTest {

    class Inner {
        void a(){
             log.info("Test.Inner");
        }
    }

    static class Inner1 {
        static void a(){
             log.info("Test.Inner1");
        }
    }

    @PostConstruct
    public void myTest(){
        TimerTest.Inner in = new TimerTest().new Inner();
        in.a();

        TimerTest.Inner1.a();
    }

}
