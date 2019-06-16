package com.gjl.weixin.gjlFactory0515;

/**
 * @Author: WilliamJL
 * @Date: 2019/5/15 16:28
 * @Version 1.0
 */
public class Factory {

    public Person getBean(String beanName){

        if(beanName .equals("student")){
            return new Student();
        }else if(beanName .equals("teacher")){
            return new Teacher();
        }else{
            return new Teacher();
        }
    }

}
