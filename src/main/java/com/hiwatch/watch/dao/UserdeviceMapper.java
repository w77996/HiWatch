package com.hiwatch.watch.dao;

import com.hiwatch.watch.entity.Userdevice;

public interface UserdeviceMapper {
    int deleteByPrimaryKey(Integer deviceId);

    int insert(Userdevice record);

    int insertSelective(Userdevice record);

    Userdevice selectByPrimaryKey(Integer deviceId);

    int updateByPrimaryKeySelective(Userdevice record);

    int updateByPrimaryKey(Userdevice record);
}