package com.hiwatch.watch.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hiwatch.watch.entity.Sleepinfo;
import com.hiwatch.watch.service.SleepService;
import com.hiwatch.watch.utils.ConstantUtils;
import com.hiwatch.watch.utils.DateUtils;
import com.hiwatch.watch.utils.JsonUtils;
import com.hiwatch.watch.utils.StringUtils;

@Controller
@RequestMapping("/sleep")
public class SleepController extends BaseController{
	private static final Logger LOG = Logger.getLogger(SleepController.class);
	@Autowired
	private SleepService sleepService;

	@ResponseBody
	@RequestMapping("/uploadsleep")
	public String uploadSleep(HttpServletRequest request){
		JSONObject jsonObject = new JSONObject();
		try {
			JSONObject jsonData = new JSONObject();
			jsonData = JsonUtils.requestJson(request);
			int userId = StringUtils.getIntFromJSon(jsonData, "userid");
			String appToken = StringUtils.getStringFromJson(jsonData, "appToken");
			JSONArray sleepData = StringUtils.getJsonArrayFromJson(jsonData, "sleepData");
			if(!verifyAppToken(userId, appToken)){
				return JsonUtils.responseJson(jsonObject, ConstantUtils.NO_LOGIN_IN);
			}
			List<Sleepinfo> sleepList = new ArrayList<Sleepinfo>();
			for (int i =0; i < sleepData.size() ;i++) {
				JSONObject sleepJson = (JSONObject) sleepData.get(i);
				int sleepStatus = StringUtils.getIntFromJSon(sleepJson, "sleepStatus");
				String sleepStart = StringUtils.getStringFromJson(sleepJson, "sleepStart");
				String sleepEnd = StringUtils.getStringFromJson(sleepJson, "sleepEnd");
				Sleepinfo sleepinfo = new Sleepinfo();
				sleepinfo.setSleepStart(DateUtils.sdfDateTime.parse(sleepStart));
				sleepinfo.setSleepEnd(DateUtils.sdfDateTime.parse(sleepEnd));
				sleepinfo.setCreateTime(new Date());
				sleepinfo.setUserId(userId);
				sleepinfo.setSleepStatus(sleepStatus);
				sleepList.add(sleepinfo);
			}
			sleepService.addSleepInfo(sleepList);
			jsonObject.put("resultCode", ConstantUtils.SUCCESS);
		} catch (Exception e) {
			// TODO: handle exception
			jsonObject.put("resultCode", ConstantUtils.EEROR);
			LOG.error("SleepController --->"+e);
		}
		return jsonObject.toJSONString();
	}
	
	@ResponseBody
	@RequestMapping("/querysleep")
	public String querysleep(HttpServletRequest request){
		JSONObject jsonObject = new JSONObject();
		try {
			JSONObject jsonData = new JSONObject();
			jsonData = JsonUtils.requestJson(request);
			int userId = StringUtils.getIntFromJSon(jsonData, "userid");
			String appToken = StringUtils.getStringFromJson(jsonData, "apptoken");
			String day = StringUtils.getStringFromJson(jsonData, "day");
			if(userId <= 0 || StringUtils.isBlank(appToken) || StringUtils.isBlank(day)){
				return JsonUtils.responseJson(jsonObject, ConstantUtils.PARAMETER_IS_NULL);
			}
			if(!verifyAppToken(userId, appToken)){
				return JsonUtils.responseJson(jsonObject, ConstantUtils.NO_LOGIN_IN);
			}
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("userId", userId);
			param.put("sleepStart", day);
			List<Sleepinfo> list = new ArrayList<>();
					list = sleepService.querySleepinfo(param);
			jsonObject.put("resultCode", ConstantUtils.SUCCESS);
			jsonObject.put("list", list);
		} catch (Exception e) {
			// TODO: handle exception
			jsonObject.put("resultCode", ConstantUtils.EEROR);
			LOG.error("SleepConstroller quertsleep error"+e);
		}
		return jsonObject.toJSONString();
	}
}
