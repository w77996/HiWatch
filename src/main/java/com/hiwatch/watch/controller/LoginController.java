package com.hiwatch.watch.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hiwatch.watch.utils.JsonUtils;


/**
 * Description:登录
 * @author w77996
 * @date 2017年7月4日 下午2:53:56
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController{
	
	private static final Logger LOG = Logger.getLogger(LoginController.class);
	
	@ResponseBody
	@RequestMapping("/login")
	public String reigst(HttpServletRequest request){
		JSONObject json = new JSONObject();
		
		JSONObject jsonData = JsonUtils.requestJson(request);
	}
}
