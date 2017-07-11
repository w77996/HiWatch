package com.hiwatch.watch.service;

import java.util.Map;

import com.hiwatch.watch.entity.Userdevice;

public interface DeviceService {

	Userdevice queryUserdevice(Map<String, Object> param);
	
	void addUserDevice(Userdevice userdevice);
}
