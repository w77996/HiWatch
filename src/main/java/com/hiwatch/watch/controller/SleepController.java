package com.hiwatch.watch.controller;

import java.util.logging.Logger;

import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hiwatch.watch.utils.JsonUtils;
import com.hiwatch.watch.utils.StringUtils;

@Controller("/sleep")
public class SleepController extends BaseController{

	@ResponseBody
	@RequestMapping("/uploadsleep")
	public String uploadSleep(HttpServletRequest request){
		JSONObject jsonObject = new JSONObject();
		try {
			JSONObject jsonData = new JSONObject();
			jsonData = JsonUtils.requestJson(request);
			int userId = StringUtils.getIntFromJSon(jsonData, "userid");
			String appToken = StringUtils.getStringFromJson(jsonData, "appToken");
			JSONArray sleepDataArray = StringUtils.getJsonArrayFromJson(jsonData, "sleepData");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
