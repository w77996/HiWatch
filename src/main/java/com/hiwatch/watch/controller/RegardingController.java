package com.hiwatch.watch.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hiwatch.watch.entity.Opoinioninfo;
import com.hiwatch.watch.entity.Versioninfo;
import com.hiwatch.watch.service.OpintionService;
import com.hiwatch.watch.service.VersionService;
import com.hiwatch.watch.utils.ConstantUtils;
import com.hiwatch.watch.utils.JsonUtils;
import com.hiwatch.watch.utils.StringUtils;
import com.hiwatch.watch.utils.ValidateUtils;

@Controller
@RequestMapping("/regarding")
public class RegardingController extends BaseController{

	
	
	@Autowired
	private OpintionService opintionService;
	@Autowired
	private VersionService versionService;
	/**
	 * 保存意见建议
	 * @Title:           saveOpinion
	 * @Description:     TODO
	 * @param:           @param request
	 * @param:           @return   
	 * @return:          String   
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/saveopinion")
	public String saveOpinion(HttpServletRequest request){
		JSONObject jsonObject = new JSONObject();
		try {
			JSONObject jsonData =  new JSONObject();
			jsonData =  JsonUtils.requestJson(request);
			int userId = StringUtils.getIntFromJSon(jsonData, "userId");
			String apptoken = StringUtils.getStringFromJson(jsonData, "apptoken");
			String opinion = StringUtils.getStringFromJson(jsonData, "opinion");
			String contact = StringUtils.getStringFromJson(jsonData, "contact");
			if(verifyAppToken(userId, apptoken)){
				return JsonUtils.responseJson(jsonObject, ConstantUtils.NO_LOGIN_IN);
			}
			if(userId <= 0 || StringUtils.isBlank(apptoken) || StringUtils.isBlank(opinion)){
				return JsonUtils.responseJson(jsonObject, ConstantUtils.PARAMETER_IS_NULL);
			}
			if(StringUtils.isBlank(contact)){
				if(!ValidateUtils.isEmail(contact) || ValidateUtils.isQQ(contact)){
					return JsonUtils.responseJson(jsonObject, ConstantUtils.PARAMETER_VALUES_ERROR);
				}
			}
			Opoinioninfo opoinioninfo = new Opoinioninfo();
			opoinioninfo.setContact(contact);
			opoinioninfo.setUserId(userId);
			opoinioninfo.setOpinionContent(opinion);
			opoinioninfo.setCreateTime(new Date());
			opintionService.addOpinion(opoinioninfo);
			jsonObject.put("resultCode",ConstantUtils.SUCCESS);
			
		} catch (Exception e) {
			// TODO: handle exception
			jsonObject.put("resultCode", ConstantUtils.EEROR);
		}
		return jsonObject.toString();
	}
	
	@ResponseBody
	@RequestMapping("/getVersion")
	public String getVersion(HttpServletRequest request){
		JSONObject jsonObject = new JSONObject();
		try {
			JSONObject jsonData = new JSONObject();
			jsonData = JsonUtils.requestJson(request);
			int userId = StringUtils.getIntFromJSon(jsonData, "userId");
			String apptoken = StringUtils.getStringFromJson(jsonData, "apptoken");
			if(userId <= 0 || StringUtils.isBlank(apptoken) ){
				return JsonUtils.responseJson(jsonObject, ConstantUtils.PARAMETER_IS_NULL);
			}
			if(verifyAppToken(userId, apptoken)){
				return JsonUtils.responseJson(jsonObject, ConstantUtils.NO_LOGIN_IN);
			}
			Versioninfo versioninfo = versionService.queryVersioninfo();
			jsonObject.put("resultCode",ConstantUtils.SUCCESS);
			jsonObject.put("version", versioninfo);
			
		} catch (Exception e) {
			// TODO: handle exception
			jsonObject.put("resultCode", ConstantUtils.EEROR);
		}
		return jsonObject.toString();
	}
	
	@ResponseBody
	@RequestMapping("/agreement")
	public String getAgreement(HttpServletRequest request){
		JSONObject jsonObject = new JSONObject();
		try {
			/*JSONObject jsonData = new JSONObject();
			jsonData = JsonUtils.requestJson(request);
			 int userId = StringUtils.getIntFromJSon(jsonData, "userId");
			 String apptoken = StringUtils.getStringFromJson(jsonData, "apptoken");
			 if(userId <= 0 || StringUtils.isBlank(apptoken) ){
					return JsonUtils.responseJson(jsonObject, ConstantUtils.PARAMETER_IS_NULL);
			 }
				if(verifyAppToken(userId, apptoken)){
					return JsonUtils.responseJson(jsonObject, ConstantUtils.NO_LOGIN_IN);
				}*/
			jsonObject.put("resultCode", ConstantUtils.SUCCESS);
			jsonObject.put("address","http:\\www.baidu.com");
		} catch (Exception e) {
			// TODO: handle exception
			jsonObject.put("resultCode", ConstantUtils.EEROR);
		}
		return jsonObject.toString();
	}
}
