package com.hiwatch.watch.service;

import java.util.Map;

import com.hiwatch.watch.entity.Verfiycode;

public interface VerifyCodeService {
	
	void addVerifyCode(Verfiycode verfiycode);
	
	Verfiycode queryVerfiycode(Map<String, Object> paramMap);
}
