package com.gjl.weixin.controller;

import com.gjl.weixin.entity.ScheduledTask;
import com.gjl.weixin.mapper.ScheduledTaskMapper;
import com.gjl.weixin.utils.ApplicationContextUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author: WilliamJL
 * @Date: 2019/5/13 15:17
 * @Version 1.0
 * 定时发邮件
 */
@Controller
@Configuration
@EnableScheduling
public class ScheduledTaskController implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        //1.添加任务内容(Runnable)
        taskRegistrar.addTriggerTask(
                new Runnable() {
                    @Override
                    public void run() {
                        SendMailController sendMailController = (SendMailController)ApplicationContextUtil.getBean("sendMailController");
                        sendMailController.taskSendMail();

                    }
                },

                //2.设置执行周期(Trigger)
                triggerContext -> {
                    //2.1 从数据库获取执行周期
                    String taskName ="sendMail";
                    ScheduledTaskMapper scheduledTaskMapper = (ScheduledTaskMapper)ApplicationContextUtil.getBean("scheduledTaskMapper");
                    String cron = scheduledTaskMapper.findTaskByKey(taskName).getTaskCron();
                    //2.3 返回执行周期(Date)
                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
                }
        );
    }


}
