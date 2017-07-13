package com.hiwatch.watch.controller;

import java.io.File;

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
	/**
	 * 是否登录验证
	 * @Title:           verifyAppToken
	 * @Description:     TODO
	 * @param:           @param userid
	 * @param:           @param appToken
	 * @param:           @return   
	 * @return:          boolean   
	 * @throws
	 */
	public boolean verifyAppToken(int userid,String appToken){
		Userinfo  userinfo  =  (Userinfo) getApplication().getAttribute(String.valueOf(userid));
		if(userinfo == null){
			return true;
		}
		if(!userinfo.getAppToken().equals(appToken)){
			return true;
		}
		return false;
	}
	/**
	 * 创建文件夹
	 * @Title:           createFloder
	 * @Description:     TODO
	 * @param:           @param path   
	 * @return:          void   
	 * @throws
	 */
	public void createFloder(String path){
		try {
			File folder = new File(path);
			if(!folder.exists()){
				folder.mkdirs();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
