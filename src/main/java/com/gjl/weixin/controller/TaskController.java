package com.gjl.weixin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gjl.weixin.entity.Pxclass;
import com.gjl.weixin.entity.ScheduledTask;
import com.gjl.weixin.mapper.ScheduledTaskMapper;
import com.gjl.weixin.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: WilliamJL
 * @Date: 2019/5/27 15:50
 * @Version 1.0
 */
@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    ScheduledTaskMapper scheduledTaskMapper;

    @RequestMapping("/findAllByCondition")
    public R findAll(String pageNum, String pageSize,ScheduledTask scheduledTask){
        if(pageNum==null){
            pageNum="1";
        }
        if(pageSize==null){
            pageSize="3";
        }
        PageHelper.startPage( Integer.valueOf(pageNum),Integer.valueOf(pageSize));
        List<ScheduledTask> list = scheduledTaskMapper.findAllByCondition(scheduledTask);
        PageInfo pageInfo = new PageInfo<ScheduledTask>(list, 3);
        if(list.size()>0){
            return R.ok(pageInfo);
        }
        return R.error("没有培训班");
    }
    @RequestMapping("/findAllTask")
    public R findAll(String pageNum, String pageSize){
        if(pageNum==null){
            pageNum="1";
        }
        if(pageSize==null){
            pageSize="3";
        }
        PageHelper.startPage( Integer.valueOf(pageNum),Integer.valueOf(pageSize));
        List<ScheduledTask> list = scheduledTaskMapper.findAll();
        PageInfo pageInfo = new PageInfo<ScheduledTask>(list, 3);
        if(list.size()>0){
            return R.ok(pageInfo);
        }
        return R.error("没有培训班");
    }
}
