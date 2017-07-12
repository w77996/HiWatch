package com.hiwatch.watch.service;

import java.util.Map;

import com.hiwatch.watch.entity.Userdevice;

public interface DeviceService {

	int queryUserdevice(Map<String, Object> param);
	
	void addUserDevice(Userdevice userdevice);
	
	Userdevice queryUserDevice(Map<String, Object> param);
	
	void deleteUserDevice(int userid);
	
}
