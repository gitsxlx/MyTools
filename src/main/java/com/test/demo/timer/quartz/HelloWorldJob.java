package com.test.demo.timer.quartz;

import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

/**
 * HelloWorldJob
 *
 * @author WangChao
 * @date 2020/04/07
 */
@Slf4j
@DisallowConcurrentExecution //作业不并发
@Component
public class HelloWorldJob implements Job {

  @Override
  public void execute(JobExecutionContext arg0) throws JobExecutionException {
    log.info("欢迎使用quartz,这是一个定时任务{}", new Date());
  }

}
