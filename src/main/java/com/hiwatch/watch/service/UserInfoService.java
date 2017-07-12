package com.hiwatch.watch.service;

import com.hiwatch.watch.entity.Userinfo;
import com.hiwatch.watch.entity.Verfiycode;


/**
 * Description:
 * @author w77996
 * @date 2017年7月4日 下午3:00:47
 */
public interface UserInfoService {
	
	Userinfo queryUserInfoByMoBile(String mobile);
	
	Userinfo queryUserInofById(int userid);
	
	void addUserInfo(Userinfo userinfo,Verfiycode vccode);
	
	
	void updateUserInfo(Userinfo userinfo);
	
	void updateUserInfoAndCode(Userinfo userinfo,Verfiycode vccode);
}
