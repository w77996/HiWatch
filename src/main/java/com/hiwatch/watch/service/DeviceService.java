package com.hiwatch.watch.service;

import java.util.Map;

import com.hiwatch.watch.entity.Userdevice;
/**
 * 
 * Description:
 * @author w77996
 * @date 2017年7月13日 下午3:38:16
 */
public interface DeviceService {

	int queryUserdevice(Map<String, Object> param);
	
	void addUserDevice(Userdevice userdevice);
	
	Userdevice queryUserDevice(Map<String, Object> param);
	
	void deleteUserDevice(int userid);
	
}
