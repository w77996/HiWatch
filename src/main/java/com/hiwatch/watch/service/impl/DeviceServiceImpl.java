package com.hiwatch.watch.service.impl;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hiwatch.watch.dao.DeviceMapper;
import com.hiwatch.watch.entity.Userdevice;
import com.hiwatch.watch.service.DeviceService;
@Service
public class DeviceServiceImpl implements DeviceService{

	private static final Logger LOG = Logger.getLogger(DeviceServiceImpl.class);
	@Autowired
	private DeviceMapper deviceMapper;

	@Override
	public int queryUserdevice(Map<String, Object> param) {
		// TODO Auto-generated method stub
		LOG.debug("queryUserDevice");
		return deviceMapper.queryUserOrDevice(param);
	}

	@Override
	public void addUserDevice(Userdevice userdevice) {
		// TODO Auto-generated method stub
		deviceMapper.insert(userdevice);
	}

	@Override
	public Userdevice queryUserDevice(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return deviceMapper.getBy(param);
	}

	@Override
	public void deleteUserDevice(int userid) {
		// TODO Auto-generated method stub
		deviceMapper.delete(userid);
	}
	
	
}
