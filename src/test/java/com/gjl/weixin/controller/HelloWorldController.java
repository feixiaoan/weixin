package com.gjl.weixin.controller;

import com.gjl.weixin.entity.Complain;
import com.gjl.weixin.mapper.ComplainMapper;
import com.gjl.weixin.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

/**
 * @Author: WilliamJL
 * @Date: 2019/5/24 14:56
 * @Version 1.0
 */
@Controller
public class HelloWorldController {

    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
    public static boolean isValidDate(String str) {
        boolean convertSuccess=true;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2019/02/29会被接受，并转换成2019/03/01
            format.setLenient(false);
            format.parse(str);
        } catch (Exception e) {
            // e.printStackTrace();
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess=false;
        }
        return convertSuccess;
    }

    public static boolean isBefore(String time1, String time2) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date1 = format.parse(time1);
            Date date2 = format.parse(time2);
            return date1.before(date2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public static int  Cdate(String time1, String time2){
        int days = 0;
        try {
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = format.parse(time1);
            Date date2 = format.parse(time2);
            days=(int)(date2.getTime()-date1.getTime())/(1000*3600*24);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return days;
    }
    public static void main(String[] args) {
        /*String str11 = "2019-02-02 12:12:12";
        String str22 = "2019-02-03 12:12:13";
        String str1 = "2019-02-30";
        String str2 = "2019-02/03";
        String str3 = "2019/02/03";
       *//* System.out.println(isValidDate(str));
        System.out.println(isValidDate(str1));
        System.out.println(isValidDate(str2));
        System.out.println(isValidDate(str3));*//*
        System.out.println(isBefore(str11,str22));*/



        String time1 = "2019-02-21";
        String time2 = "2019-02-21";
        int cdate = Cdate(time1, time2);
        System.out.println(cdate);


    }

}
