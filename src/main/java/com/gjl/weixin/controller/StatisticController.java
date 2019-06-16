package com.gjl.weixin.controller;

import com.gjl.weixin.controller.export.ExportWord;
import com.gjl.weixin.entity.Statistic;
import com.gjl.weixin.entity.Student;
import com.gjl.weixin.mapper.PxclassMapper;
import com.gjl.weixin.service.StatisticService;
import com.gjl.weixin.utils.DataUtil;
import com.gjl.weixin.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class StatisticController {

    @Autowired
    private StatisticService statisticService;
    @Autowired
    private PxclassMapper pxclassMapper;

    @Autowired
    private ExportWord exportWord;


    //统计表格输出班级名字.doxc
    @RequestMapping("/count")
    public R count(String createTime ,String endTime ,String className,String id, HttpServletRequest request, HttpServletResponse response){

        if(createTime == null || endTime == null){
            return R.error("请输入日期");
        }
        Boolean isValidCreateTime = DataUtil.isValidDate(createTime);
        Boolean isValidEndTime = DataUtil.isValidDate(endTime);
        if(!isValidCreateTime||!isValidEndTime){
            return R.error("日期格式不对");
        }
        if(!DataUtil.isBefore(createTime,endTime)){
            return R.error("开始时间在结束时间之前");
        }

        className = pxclassMapper.findPxclassById(id).get(0).getClassName();
        //List<Statistic> list = statisticService.findStatisticByGroupPxclass(className);
        List<Statistic> list = statisticService.findStatisticByGroupPxclassTime(className,createTime ,endTime);
        int total = statisticService.findTotalByClassName()/20;
        //二维数组顺序代表统计表格
        int[][] ints = new int[20][5];

        for(int i=0;i<ints.length;i++){
            String questionOrder = String.valueOf((i+1));
            ints[i][0]=(int)list.stream().filter(x->
                    (questionOrder.equals(String.valueOf(x.getQuestionId()))&&"a".equals(x.getAnswer()))).count();
            ints[i][1]=(int)list.stream().filter(x->
                    (questionOrder.equals(String.valueOf(x.getQuestionId()))&&"b".equals(x.getAnswer()))).count();
            ints[i][2]=(int)list.stream().filter(x->
                    (questionOrder.equals(String.valueOf(x.getQuestionId()))&&"c".equals(x.getAnswer()))).count();
            ints[i][3]=(int)list.stream().filter(x->
                    (questionOrder.equals(String.valueOf(x.getQuestionId()))&&"d".equals(x.getAnswer()))).count();
            total = ints[i][0]+ints[i][1]+ints[i][2]+ints[i][3];
            ints[i][4]=(ints[i][0]*115+ints[i][1]*100+ints[i][2]*80)/total;

        }
        /*int question1a=list.stream().filter(x->
                ("1".equals(String.valueOf(x.getQuestionId()))&&"a".equals(x.getAnswer()))).collect(Collectors.toList()).size();
        int question1b=list.stream().filter(x->
                ("1".equals(String.valueOf(x.getQuestionId()))&&"b".equals(x.getAnswer()))).collect(Collectors.toList()).size();
        int question1c=list.stream().filter(x->
                ("1".equals(String.valueOf(x.getQuestionId()))&&"c".equals(x.getAnswer()))).collect(Collectors.toList()).size();
        int question1d=list.stream().filter(x->
                ("1".equals(String.valueOf(x.getQuestionId()))&&"d".equals(x.getAnswer()))).collect(Collectors.toList()).size();
*/

        exportWord.export(request,response,ints,className);
        System.out.println(ints);
         if(ints.length>0){
             return R.ok();
        }
        return R.error();
    }
}
