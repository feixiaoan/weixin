package com.gjl.weixin.config;

import com.gjl.weixin.interceptor.SysInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class SessionConfiguration extends WebMvcConfigurerAdapter {

    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new SysInterceptor()).addPathPatterns("/**").excludePathPatterns("/adminUser/login");
        //registry.addInterceptor(new SysInterceptor()).addPathPatterns("/**").excludePathPatterns("/student/login");
    }
}
