package com.hiwatch.watch.dao;

import java.util.Map;

import com.hiwatch.watch.entity.Userdevice;
/**
 * 用户设备绑定dao
 * Description:
 * @author w77996
 * @date 2017年7月11日 下午7:13:07
 */
public interface DeviceMapper  extends BaseDao<Userdevice>{
	
	int queryUserOrDevice(Map<String, Object> param);
}
