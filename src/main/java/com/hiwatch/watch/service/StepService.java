package com.hiwatch.watch.service;

import java.util.List;
import java.util.Map;

import com.hiwatch.watch.entity.Stepinfo;

public interface StepService {

	void addStepList(List<Stepinfo> list);
	
	void addStepinfo(Stepinfo stepinfo);
	
	List<Stepinfo>  queryStepList(Map<String,Object> param);
	
	Stepinfo queryStepinfo(Map<String,Object> param);
	
	void updateStepinfo(Stepinfo stepinfo);
}
