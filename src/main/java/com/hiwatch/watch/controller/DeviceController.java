package com.hiwatch.watch.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hiwatch.watch.entity.Userdevice;
import com.hiwatch.watch.service.DeviceService;
import com.hiwatch.watch.utils.ConstantUtils;
import com.hiwatch.watch.utils.JsonUtils;
import com.hiwatch.watch.utils.StringUtils;
/**
 * 设备处理controller
 * Description:
 * @author w77996
 * @date 2017年7月11日 下午5:45:25
 */
@Controller
@RequestMapping("/device")
public class DeviceController extends BaseController {
	
	@Autowired
	private DeviceService deviceService;
	/**
	 * 绑定设备
	 * @Title:           bounddevice
	 * @Description:     TODO
	 * @param:           @param request
	 * @param:           @return   
	 * @return:          String   
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/bound")
	public String bounddevice(HttpServletRequest request){
		JSONObject jsonObject = new JSONObject();
		try {
			JSONObject jsonData =  new JSONObject();
			jsonData = JsonUtils.requestJson(request);
			String appToken = StringUtils.getStringFromJson(jsonData, "apptoken");
			String deviceImei =  StringUtils.getStringFromJson(jsonData, "deviceImei");
			String deviceName = StringUtils.getStringFromJson(jsonData, "deviceName");
			int userId = StringUtils.getIntFromJSon(jsonData, "userId");
			
			if(StringUtils.isBlank(appToken) || StringUtils.isBlank(deviceImei) || StringUtils.isBlank(deviceName) || userId <= 0){
				return JsonUtils.responseJson(jsonObject, ConstantUtils.PARAMETER_IS_NULL);
			}
			if(verifyAppToken(userId, appToken)){
				return JsonUtils.responseJson(jsonObject, ConstantUtils.NO_LOGIN_IN);
			}
			Map<String, Object> param =  new HashMap<String, Object>();
			param.put("userId", userId);
			param.put("deviceImei", deviceImei);
			Userdevice userdevice = deviceService.queryUserdevice(param);
			if(userdevice != null){
				return JsonUtils.responseJson(jsonObject, ConstantUtils.USER_OR_DEVICE_BOUND);
			}
			Userdevice userdeviceData =  new Userdevice();
			userdeviceData.setDeviceImei(deviceImei);
			userdeviceData.setDeviceName(deviceName);
			userdeviceData.setCreateTime(new Date());
			deviceService.addUserDevice(userdeviceData);
			jsonObject.put("resuleCode", ConstantUtils.SUCCESS);
		} catch (Exception e) {
			// TODO: handle exception
			jsonObject.put("resultCode", ConstantUtils.EEROR);
		}
		return jsonObject.toString();
	}
	/**
	 * 获取绑定设备
	 * @Title:           getdevice
	 * @Description:     TODO
	 * @param:           @param request
	 * @param:           @return   
	 * @return:          String   
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/getdevice")
	public String getdevice(HttpServletRequest request){
		JSONObject jsonObject = new JSONObject();
		try {
			JSONObject jsonData =  new JSONObject();
			jsonData = JsonUtils.requestJson(request);
			int userid = StringUtils.getIntFromJSon(jsonData, "userId");
			String apptoken = StringUtils.getStringFromJson(jsonData, "apptoken");
			if(userid <=0 || StringUtils.isBlank(apptoken)){
				return JsonUtils.responseJson(jsonObject, ConstantUtils.PARAMETER_IS_NULL);
			}
			if(verifyAppToken(userid, apptoken)){
				return JsonUtils.responseJson(jsonObject, ConstantUtils.NO_LOGIN_IN);
			}
			Map<String, Object> param = new HashMap<String,Object>();
			param.put("userId", userid);
			param.put("apptoken",apptoken);
			Userdevice userdevice = deviceService.queryUserdevice(param);
			jsonObject.put("resultCode",ConstantUtils.SUCCESS);
			jsonObject.put("device", userdevice);
		} catch (Exception e) {
			// TODO: handle exception
			jsonObject.put("resultCode",ConstantUtils.EEROR);
		}
		return jsonObject.toString();
	}

}
