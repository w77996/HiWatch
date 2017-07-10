package com.hiwatch.watch.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hiwatch.watch.dao.SleepinfoMapper;
import com.hiwatch.watch.entity.Sleepinfo;
import com.hiwatch.watch.service.SleepService;
@Service
public class SleepServiceImpl implements SleepService {

	@Autowired
	private SleepinfoMapper sleepinfoMapper;
	@Override
	public void addSleepInfo(List<Sleepinfo> list) {
		// TODO Auto-generated method stub
		sleepinfoMapper.insert(list);

	}

	@Override
	public List<Sleepinfo> querySleepinfo(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return sleepinfoMapper.listBy(param);
	}

}
