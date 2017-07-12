package com.hiwatch.watch.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hiwatch.watch.dao.OpintionMapper;
import com.hiwatch.watch.entity.Opoinioninfo;
import com.hiwatch.watch.service.OpintionService;
@Service
public class OpintionServiceImpl implements OpintionService {

	@Autowired 
	private OpintionMapper opintionMapper;
	@Override
	public void addOpinion(Opoinioninfo opoinioninfo) {
		// TODO Auto-generated method stub
		opintionMapper.insert(opoinioninfo);
	}

}
