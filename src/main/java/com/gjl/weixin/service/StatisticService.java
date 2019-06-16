package com.gjl.weixin.service;

import com.gjl.weixin.entity.Statistic;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StatisticService {

    public List<Statistic> findStatisticByGroupPxclass(String className);

    int  findTotalByClassName();

    List<Statistic> findStatisticByGroupPxclassTime(String className, String createTime, String endTime);
}
