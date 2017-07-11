package com.hiwatch.watch.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hiwatch.watch.dao.DeviceMapper;
import com.hiwatch.watch.entity.Userdevice;
import com.hiwatch.watch.service.DeviceService;
@Service
public class DeviceServiceImpl implements DeviceService{

	@Autowired
	private DeviceMapper deviceMapper;

	@Override
	public Userdevice queryUserdevice(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return deviceMapper.getBy(param);
	}

	@Override
	public void addUserDevice(Userdevice userdevice) {
		// TODO Auto-generated method stub
		deviceMapper.insert(userdevice);
	}
	
	
}
