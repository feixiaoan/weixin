package com.gjl.weixin.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: WilliamJL
 * @Date: 2019/6/3 22:35
 * @Version 1.0
 */
public class DataUtil {


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

    public static String  Cdate(String time1, String time2){
        int days = 0;
        try {
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = format.parse(time1);
            Date date2 = format.parse(time2);
            days=(int)(date2.getTime()-date1.getTime())/(1000*3600*24);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return String.valueOf(Math.abs(days));
    }
}
