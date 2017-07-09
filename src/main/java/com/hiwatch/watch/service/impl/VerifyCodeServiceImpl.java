package com.hiwatch.watch.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hiwatch.watch.dao.VerfiycodeMapper;
import com.hiwatch.watch.entity.Verfiycode;
import com.hiwatch.watch.service.VerifyCodeService;
@Service("VerifyCodeService")
public class VerifyCodeServiceImpl implements VerifyCodeService {
	
	@Autowired
	private VerfiycodeMapper verfiycodeMapper;

	@Override
	public void addVerifyCode(Verfiycode verfiycode) {
		// TODO Auto-generated method stub
		int result = verfiycodeMapper.insert(verfiycode);
		System.out.println("insert result :"+ result);
	}

	@Override
	public Verfiycode queryVerfiycode(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return verfiycodeMapper.getBy(paramMap);
	}

}
