package com.hiwatch.watch.service;

import java.util.List;
import java.util.Map;

import com.hiwatch.watch.entity.Sleepinfo;

public interface SleepService {
	void addSleepInfo(List<Sleepinfo> list);
	
	List<Sleepinfo>  querySleepinfo(Map<String,Object> param);

}
