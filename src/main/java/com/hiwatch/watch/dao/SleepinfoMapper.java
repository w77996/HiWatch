package com.hiwatch.watch.dao;

import com.hiwatch.watch.entity.Sleepinfo;

public interface SleepinfoMapper {
    int deleteByPrimaryKey(Integer sleepId);

    int insert(Sleepinfo record);

    int insertSelective(Sleepinfo record);

    Sleepinfo selectByPrimaryKey(Integer sleepId);

    int updateByPrimaryKeySelective(Sleepinfo record);

    int updateByPrimaryKey(Sleepinfo record);
}