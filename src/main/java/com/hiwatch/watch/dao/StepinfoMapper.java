package com.hiwatch.watch.dao;

import com.hiwatch.watch.entity.Stepinfo;

public interface StepinfoMapper {
    int deleteByPrimaryKey(Integer stepId);

    int insert(Stepinfo record);

    int insertSelective(Stepinfo record);

    Stepinfo selectByPrimaryKey(Integer stepId);

    int updateByPrimaryKeySelective(Stepinfo record);

    int updateByPrimaryKey(Stepinfo record);
}