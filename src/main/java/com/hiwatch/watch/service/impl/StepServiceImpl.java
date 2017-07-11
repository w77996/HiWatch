package com.hiwatch.watch.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hiwatch.watch.dao.StepinfoMapper;
import com.hiwatch.watch.entity.Stepinfo;
import com.hiwatch.watch.service.StepService;
@Service
public class StepServiceImpl implements StepService {

	@Autowired
	private StepinfoMapper stepinfoMapper;
	@Override
	public void addStepList(List<Stepinfo> list) {
		// TODO Auto-generated method stub
		stepinfoMapper.insert(list);
	}

	@Override
	public List<Stepinfo> queryStepList(Map<String, Object> param) {
		// TODO Auto-generated method stub
		
		return stepinfoMapper.listBy(param);
	}

	@Override
	public Stepinfo queryStepinfo(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return stepinfoMapper.getBy(param);
	}

	@Override
	public void updateStepinfo(Stepinfo stepinfo) {
		// TODO Auto-generated method stub
		stepinfoMapper.update(stepinfo);
	}

	@Override
	public void addStepinfo(Stepinfo stepinfo) {
		// TODO Auto-generated method stub
		stepinfoMapper.insert(stepinfo);
	}

}
