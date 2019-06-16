package com.gjl.weixin.common;

import org.springframework.stereotype.Controller;

@Controller
public class GlobalError {
    /** 产品系列下已存在产品,不允许修改投资锁定期属性*/
    public static final String ERROR_HASISSUE_LOCKCHANGEFORBID = "error.hasissue.lockchangeforbid";
    /** 系统参数只能为1 或 0*/
    public static final String ERROR_SYSPARAM_VALUE = "参数值不合法只能为1 或 0";
    /** 系统中没有此参数*/
    public static final String ERROR_SYS_PARAM = "系统中没有此参数";
    /** 定时任务未开启*/
    public static final String ERROR_SCH_TASK = "定时任务未开启";
    /** 投诉表中无数据*/
    public static final String ERROR_COMPLAIN_DATA = "投诉表中无数据";
    /** 评价时间未开启*/
    public static final String ERROR_EVALUATE_DATA = "评价时间未开启";

}
