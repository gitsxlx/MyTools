package com.tool.demo.timer.timertask;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 定时任务
 * 3、Timer
 * java自带的
 *
 * @author WangChao
 * @date 2020/04/07
 */
@Slf4j
@Component
public class TimerTaskTest {

  /*使用Timer的schedule，schedule有3个参数：
    schedule(TimerTask task, long delay, long period)
    task为定时任务，根据业务需要重写TimerTask的run方法即可。
    delay为延时启动，单位毫秒。
    period为多久运行一次，单位毫秒。*/

    @PostConstruct
    public void myTimerTask(){
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                log.info("TimerTask启动了 {}", new Date());
            }
        }, 1000L, 2 * 1000L);
    }

}
