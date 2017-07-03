package com.hiwatch.watch.dao;

import com.hiwatch.watch.entity.Verfiycode;

public interface VerfiycodeMapper {
    int deleteByPrimaryKey(Integer verfiyId);

    int insert(Verfiycode record);

    int insertSelective(Verfiycode record);

    Verfiycode selectByPrimaryKey(Integer verfiyId);

    int updateByPrimaryKeySelective(Verfiycode record);

    int updateByPrimaryKey(Verfiycode record);
}