package com.gjl.weixin.mapper;

import com.gjl.weixin.entity.ScheduledTask;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScheduledTaskMapper {
    int insert(ScheduledTask record);

    int insertSelective(ScheduledTask record);

    List<ScheduledTask> findAll();

    ScheduledTask findTaskByKey(String taskName);

    List<ScheduledTask> findAllByCondition(ScheduledTask scheduledTask);
}