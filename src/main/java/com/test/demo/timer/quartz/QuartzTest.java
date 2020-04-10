package com.test.demo.timer.quartz;

import static org.quartz.CronScheduleBuilder.*;
import static org.quartz.DateBuilder.*;
import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.DateBuilder.IntervalUnit;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

/**
 * 定时任务
 * 1、quartz
 * 具体开发应用时应使用模块化方式将各模块分开
 *
 * http://www.quartz-scheduler.org/api/2.3.0/index.html
 *
 * @author WangChao
 * @date 2020/04/07
 */
@Slf4j
@Component
public class QuartzTest {

    /*quartz使用的是可配置的方式，将所有的定时器都配置在一个xml文件里面。一般步骤如下：
        1.创建一个spring的配置文件：spring-quartz.xml。
        2.定义工作任务的job。
        3.定义触发器Trigger并与job绑定。
        4.定义调度器，并将Trigger注册到scheduler。*/

      /*<bean id="myTask" class="cn.coolwind.MyTask"/>
      <!-- 1.定义工作任务job -->
      <bean id="testJob" class="org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean">
          <!-- 定时器的类  -->
          <property name="targetObject" ref="myTask"></property>
          <!-- 需要定时执行的方法  -->
          <property name="targetMethod" value="test"></property>
          <property name="concurrent" value="false"></property>
      </bean>
      <!-- 2.定义触发器Trigger并与Job绑定 -->
      <bean id="testJobTrigger" class="org.springframework.scheduling.quartz.CronTriggerFactoryBean">
          <property name="jobDetail" ref="testJob"/>
          <!-- 根据需要设置定时执行的时间 -->
          <property name="cronExpression" value="0 0/5 * * * ?" />
      </bean>

      <!-- 3.定义调度器，并将trigger注册进去 -->
      <bean name="quartzScheduler" class="org.springframework.scheduling.quartz.SchedulerFactoryBean">
          <property name="triggers">
              <list>
                   <ref local="testJobTrigger" />
              </list>
          </property>
      </bean>*/

    @Bean(name = "schedulerFactoryBean")
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        try {
            schedulerFactoryBean.setOverwriteExistingJobs(true);
            //schedulerFactoryBean.setQuartzProperties(quartzProperties());
            //schedulerFactoryBean.setJobFactory(jobFactory);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return schedulerFactoryBean;
    }

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    // 创建schedule
    @Bean(name = "scheduler")
    public Scheduler scheduler() {
        return schedulerFactoryBean.getScheduler();
    }

    @Autowired
    private Scheduler scheduler;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    static class MyTask{
        private String jobName;
        private String description;
        private String cronExpression;
        private String jobStatus;
        private String jobGroup;
        private String beanClass;
    }

    @PostConstruct
    public void myJob() {
        MyTask task = new MyTask("helloWorld" ,"跑批配置测试", "0/10 * * * * ?", "0", "group", "com.test.demo.timer.quartz.HelloWorldJob");
        MyTask myTask = MyTask.builder()
            .jobName("hello")
            .description("jobDataMap配置测试")
            .cronExpression("0/8 * * * * ?")
            .jobStatus("0")
            .jobGroup("group")
            .beanClass(HelloJob.class.getName().toString())
            .build();

        addJob(task);
        addJob(myTask);
    }

    /**
     * 添加任务
     *
     * @param task
     * @throws SchedulerException
     */
    public void addJob(MyTask task) {
        log.info("quartz开始装载...");
        try {
            // 创建jobDetail实例，绑定Job实现类
            // 指明job的名称，所在组的名称，以及绑定job类
            Class<? extends Job> jobClass = (Class<? extends Job>) (Class.forName(task.getBeanClass()).getDeclaredConstructor().newInstance()
                .getClass());

            JobDetail jobDetail = newJob(jobClass)
                .withIdentity(task.getJobName(), task.getJobGroup()) // 任务名称和组构成任务key
                .usingJobData("jobSays", "Hello World!")
                .usingJobData("myFloatValue", 3.141f)
                .build();

            // 定义调度触发规则
            // 使用cornTrigger规则
            Trigger trigger = newTrigger()
                .withIdentity(task.getJobName(), task.getJobGroup())// 触发器key
                .startAt(futureDate(1, IntervalUnit.SECOND))
                .withSchedule(cronSchedule(task.getCronExpression()))
                .startNow()
                .build();

            // 把作业和触发器注册到任务调度中
            scheduler.scheduleJob(jobDetail, trigger);

            // 启动
            if (!scheduler.isShutdown()) {
                scheduler.start();
            }
            log.info("quartz start!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 所有正在运行的job
     *
     * @return
     * @throws SchedulerException
     */
    public List<MyTask> getRunningJob() throws SchedulerException {
        List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
        List<MyTask> jobList = new ArrayList<MyTask>(executingJobs.size());
        for (JobExecutionContext executingJob : executingJobs) {
            MyTask job = new MyTask();
            JobDetail jobDetail = executingJob.getJobDetail();
            JobKey jobKey = jobDetail.getKey();
            Trigger trigger = executingJob.getTrigger();
            job.setJobName(jobKey.getName());
            job.setJobGroup(jobKey.getGroup());
            job.setDescription("触发器:" + trigger.getKey());
            Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
            job.setJobStatus(triggerState.name());
            if (trigger instanceof CronTrigger) {
                CronTrigger cronTrigger = (CronTrigger) trigger;
                String cronExpression = cronTrigger.getCronExpression();
                job.setCronExpression(cronExpression);
            }
            jobList.add(job);
        }
        return jobList;
    }

    /**
     * 暂停一个job
     *
     * @param task
     * @throws SchedulerException
     */
    public void pauseJob(MyTask task) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(task.getJobName(), task.getJobGroup());
        scheduler.pauseJob(jobKey);
    }

    /**
     * 恢复一个job
     *
     * @param task
     * @throws SchedulerException
     */
    public void resumeJob(MyTask task) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(task.getJobName(), task.getJobGroup());
        scheduler.resumeJob(jobKey);
    }

    /**
     * 删除一个job
     *
     * @param task
     * @throws SchedulerException
     */
    public void deleteJob(MyTask task) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(task.getJobName(), task.getJobGroup());
        scheduler.deleteJob(jobKey);
    }

    /**
     * 立即执行job
     *
     * @param task
     * @throws SchedulerException
     */
    public void runJobNow(MyTask task) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(task.getJobName(), task.getJobGroup());
        scheduler.triggerJob(jobKey);
    }

    /**
     * 更新job时间表达式
     *
     * @param task
     * @throws SchedulerException
     */
    public void updateJobCron(MyTask task) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(task.getJobName(), task.getJobGroup());
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        CronScheduleBuilder scheduleBuilder = cronSchedule(task.getCronExpression());
        trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
        scheduler.rescheduleJob(triggerKey, trigger);
    }

}
