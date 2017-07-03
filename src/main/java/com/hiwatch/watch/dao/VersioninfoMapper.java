package com.hiwatch.watch.dao;

import com.hiwatch.watch.entity.Versioninfo;

public interface VersioninfoMapper {
    int deleteByPrimaryKey(Integer versionId);

    int insert(Versioninfo record);

    int insertSelective(Versioninfo record);

    Versioninfo selectByPrimaryKey(Integer versionId);

    int updateByPrimaryKeySelective(Versioninfo record);

    int updateByPrimaryKeyWithBLOBs(Versioninfo record);

    int updateByPrimaryKey(Versioninfo record);
}