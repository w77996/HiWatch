package com.hiwatch.watch.dao;

import com.hiwatch.watch.entity.Opoinioninfo;

public interface OpoinioninfoMapper {
    int deleteByPrimaryKey(Integer opinionId);

    int insert(Opoinioninfo record);

    int insertSelective(Opoinioninfo record);

    Opoinioninfo selectByPrimaryKey(Integer opinionId);

    int updateByPrimaryKeySelective(Opoinioninfo record);

    int updateByPrimaryKeyWithBLOBs(Opoinioninfo record);

    int updateByPrimaryKey(Opoinioninfo record);
}