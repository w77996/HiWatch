package com.hiwatch.watch.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;


import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hiwatch.watch.entity.Stepinfo;
import com.hiwatch.watch.service.StepService;
import com.hiwatch.watch.utils.ConstantUtils;
import com.hiwatch.watch.utils.DateUtils;
import com.hiwatch.watch.utils.JsonUtils;
import com.hiwatch.watch.utils.StringUtils;

@Controller
@RequestMapping("/step")
public class StepController extends BaseController {

	private static final Logger LOG = Logger.getLogger(StepController.class);
	@Autowired
	private StepService stepService;
	/**
	 * 上传步数
	 * @Title:           uploadstep
	 * @Description:     TODO
	 * @param:           @param request
	 * @param:           @return   
	 * @return:          String   
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/uploadstep")
	public String uploadstep(HttpServletRequest request){
		JSONObject jsonObject = new JSONObject();
		try {
			JSONObject jsonData = new JSONObject();
			
			jsonData = JsonUtils.requestJson(request);
			LOG.debug(jsonData);
			String apptoken = StringUtils.getStringFromJson(jsonData, "apptoken");
			int userid =StringUtils.getIntFromJSon(jsonData, "userid");
			JSONArray jsonArray = StringUtils.getJsonArrayFromJson(jsonData, "stepData");
			LOG.debug(apptoken+ " " + userid + " " +jsonArray);
			if(userid <= 0 || StringUtils.isBlank(apptoken) || StringUtils.isEmpty(jsonArray)){
				return JsonUtils.responseJson(jsonObject, ConstantUtils.PARAMETER_IS_NULL);
			}
			/*if(verifyAppToken(userid, apptoken)){
				return JsonUtils.responseJson(jsonObject, ConstantUtils.NO_LOGIN_IN);
			}*/
			Stepinfo stepinfo = new Stepinfo();
			for(int i=0; i<jsonArray.size();i++){
				JSONObject json = new JSONObject();
				json = (JSONObject) jsonArray.get(i);
				int stepNum = StringUtils.getIntFromJSon(json, "stepNum");
				int calorie = StringUtils.getIntFromJSon(json, "calorie");
				int timeKey = StringUtils.getIntFromJSon(json, "timeKey");
				float mileage = StringUtils.getFloatFromJson(json, "meileage");
				String creatTime = StringUtils.getStringFromJson(json, "createTime");
				if(stepNum < 0 || calorie <0 || timeKey < 0|| mileage < 0){
					continue;
				}
				Map<String, Object> paraMap = new HashMap<String, Object>();
				paraMap.put("userId", userid);
				paraMap.put("createTime", creatTime);
				paraMap.put("apptoken", apptoken);
				paraMap.put("timeKey", timeKey);
				Stepinfo stepdata = stepService.queryStepinfo(paraMap);
				if(stepdata != null){
					stepdata.setStepNum(stepNum + stepdata.getStepNum());
					stepdata.setCalorie(calorie + stepdata.getCalorie());
					stepdata.setMileage(mileage + stepdata.getMileage());
					stepService.updateStepinfo(stepdata);
				}else{
					stepinfo.setUserId(userid);
					stepinfo.setCalorie(calorie);
					stepinfo.setMileage(mileage);
					stepinfo.setStepNum(stepNum);
					stepinfo.setCreateTime(DateUtils.sdfDateOnly.parse(creatTime));
					stepinfo.setTimeKey(timeKey);
					stepService.addStepinfo(stepinfo);
				}
			}
			jsonObject.put("resultCode", ConstantUtils.SUCCESS);
		} catch (Exception e) {
			jsonObject.put("resultCode",ConstantUtils.EEROR);
		}
		return jsonObject.toString();
	}
	/**
	 * 查询步数
	 * @Title:           queryStep
	 * @Description:     TODO
	 * @param:           @param request
	 * @param:           @return   
	 * @return:          String   
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/querystep")
	public String queryStep(HttpServletRequest request){
		JSONObject jsonObject = new JSONObject();
		try {
			JSONObject jsonData = new JSONObject();
			jsonData = JsonUtils.requestJson(request);
			int userid = StringUtils.getIntFromJSon(jsonData, "userid");
			String apptoken = StringUtils.getStringFromJson(jsonData, "apptoken");
			String stepTime = StringUtils.getStringFromJson(jsonData, "stepTime");
			if(userid <= 0 || StringUtils.isBlank(apptoken) || StringUtils.isBlank(stepTime)){
				return JsonUtils.responseJson(jsonObject, ConstantUtils.PARAMETER_IS_NULL);
			}
			if(verifyAppToken(userid, apptoken)){
				return JsonUtils.responseJson(jsonObject, ConstantUtils.NO_LOGIN_IN);
			}
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("userId", userid);
			param.put("createTime", stepTime);
			List<Stepinfo> stepinfoList = stepService.queryStepList(param);
			jsonObject.put("resultCode", ConstantUtils.SUCCESS);
			jsonObject.put("list", stepinfoList);
		} catch (Exception e) {
			// TODO: handle exception
			jsonObject.put("resultCode", ConstantUtils.EEROR);
		}
		return jsonObject.toString();
	}
}
