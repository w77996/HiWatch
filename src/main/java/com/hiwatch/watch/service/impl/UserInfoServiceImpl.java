package com.hiwatch.watch.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hiwatch.watch.dao.UserinfoMapper;
import com.hiwatch.watch.dao.VerfiycodeMapper;
import com.hiwatch.watch.entity.Userinfo;
import com.hiwatch.watch.entity.Verfiycode;
import com.hiwatch.watch.service.UserInfoService;
@Service
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private UserinfoMapper userinfoMapper;
	@Autowired
	private VerfiycodeMapper  verfiycodeMapper;
	@Override
	public Userinfo queryUserInfoByMoBile(String mobile) {
		// TODO Auto-generated method stub
		Map<String , Object> paramMap = new HashMap<String,Object>();
		paramMap.put("userAccount", mobile);
		return userinfoMapper.getBy(paramMap);
	}

	@Override
	public void addUserInfo(Userinfo userinfo, Verfiycode vccode) {
		// TODO Auto-generated method stub
		userinfoMapper.insert(userinfo);
		verfiycodeMapper.update(vccode); 
	}



	@Override
	public void updateUserInfo(Userinfo userinfo) {
		// TODO Auto-generated method stub
		userinfoMapper.update(userinfo);

	}

	@Override
	public void updateUserInfoAndCode(Userinfo userinfo, Verfiycode vccode) {
		// TODO Auto-generated method stub

	}

	@Override
	public Userinfo queryUserInofById(int userid) {
		// TODO Auto-generated method stub
		return userinfoMapper.getById(userid);
	}

}
