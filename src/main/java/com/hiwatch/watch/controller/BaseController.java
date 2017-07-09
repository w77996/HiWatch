package com.hiwatch.watch.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.hiwatch.watch.entity.Userinfo;

public abstract class BaseController {
	
	protected ServletContext getApplication() {
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession().getServletContext();
	}
	protected HttpSession getSession(){
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession();
	}
	public boolean verifyAppToken(int userid,String appToken){
		Userinfo  userinfo  =  (Userinfo) getApplication().getAttribute(String.valueOf(userid));
		if(userinfo == null){
			return false;
		}
		if(!userinfo.getAppToken().equals(appToken)){
			return true;
		}
		return false;
	}

}
