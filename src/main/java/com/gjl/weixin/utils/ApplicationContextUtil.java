package com.gjl.weixin.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;

/**
 * @Author: WilliamJL
 * @Date: 2019/5/14 9:09
 * @Version 1.0
 * 配置类，解决定时任务无法注入的问题
 */
@Controller
public class ApplicationContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextUtil.applicationContext = applicationContext;

    }


    public static Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }
}
