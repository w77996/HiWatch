package com.hiwatch.watch.controller;

import java.io.File;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSONObject;
import com.hiwatch.watch.entity.Userinfo;
import com.hiwatch.watch.service.UserInfoService;
import com.hiwatch.watch.utils.ConstantUtils;
import com.hiwatch.watch.utils.JsonUtils;
import com.hiwatch.watch.utils.StringUtils;

/**
 * 用户信息
 * Description:
 * @author w77996
 * @date 2017年7月12日 下午2:22:11
 */
@Controller
@RequestMapping("/setting")
public class UserController extends BaseController{

	private static final Logger LOG = Logger.getLogger(UserController.class);
	@Autowired
	private UserInfoService userInfoService;
	/**
	 * 上传头像
	 * @Title:           setPhoto
	 * @Description:     TODO
	 * @param:           @param request
	 * @param:           @return   
	 * @return:          String   
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/photo")
	public String setPhoto(HttpServletRequest request){
		JSONObject jsonObject = new JSONObject();
		try {
			String web = System.getProperty("user.dir").replace("bin", "webapps");
			LOG.debug(web);
			String webapps = ConstantUtils.TOMCAT_URL;
			webapps+=ConstantUtils.USUE_POTHO_URL;
			
			int userId = Integer.valueOf(request.getParameter("userId"));
			String appToken = request.getParameter("apptoken");
			
			if(verifyAppToken(userId, appToken)){
				return JsonUtils.responseJson(jsonObject, ConstantUtils.PARAMETER_IS_NULL);
			}
			if(StringUtils.isBlank(appToken) || userId <= 0){
				return JsonUtils.responseJson(jsonObject, ConstantUtils.PARAMETER_IS_NULL);
			}
			//spring自带的上传工具
			CommonsMultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
			if(resolver.isMultipart(request)){
				MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
				Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
				while(iterator.hasNext()){
					MultipartFile file = multipartHttpServletRequest.getFile(iterator.next());
					if(file != null){
						String myfilename = file.getOriginalFilename();
						//获取文件后缀
						String suffix = StringUtils.getExt(myfilename);
						
						if("GIF".equalsIgnoreCase(suffix) || "JPEG".equalsIgnoreCase(suffix) ||"JPG".equalsIgnoreCase(suffix) || "PNG".equalsIgnoreCase(suffix)){
							String filename = new Date().getTime()+"."+suffix;
							LOG.info(filename);
							createFloder(webapps);//创建文件夹
							String path = webapps + filename;
							LOG.info("图片保存路径"+path);
							File localFile = new File(path);
							file.transferTo(localFile);//Transfer the received file to the given destination file. 
							Userinfo userinfo = (Userinfo) getApplication().getAttribute(String.valueOf(userId));
							userinfo.setUserId(userId);
							userinfo.setPhoto(ConstantUtils.USUE_POTHO_URL+filename);
							userInfoService.updateUserInfo(userinfo);
							getApplication().setAttribute(String.valueOf(userId),userinfo);//更新session中的userinfo
							jsonObject.put("resultCode", ConstantUtils.SUCCESS);
							jsonObject.put("filename",ConstantUtils.USUE_POTHO_URL + filename);
						}else{
							jsonObject.put("resultCode",ConstantUtils.PARAMETER_IS_NULL);
						}
					}else{
						jsonObject.put("resultCode",ConstantUtils.PARAMETER_VALUES_ERROR);
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			jsonObject.put("resultCode",ConstantUtils.EEROR);
		}
		return jsonObject.toString();
	}
	/**
	 * 设置用户信息
	 * @Title:           setUserinfo
	 * @Description:     TODO
	 * @param:           @param request
	 * @param:           @return   
	 * @return:          String   
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/setUserinfo")
	public String setUserinfo(HttpServletRequest request){
		JSONObject jsonObject = new JSONObject();
		try {
			JSONObject jsonData = JsonUtils.requestJson(request);
			int userId = StringUtils.getIntFromJSon(jsonData, "userId");
			String apptoken = StringUtils.getStringFromJson(jsonData, "appToken");
			String userName = StringUtils.getStringFromJson(jsonData, "userName");
			int userSex = StringUtils.getIntFromJSon(jsonData,"userSex");
			Date birthday = StringUtils.getDateFromJson(jsonData, "birthDay");
			int height = StringUtils.getIntFromJSon(jsonData, "height");
			int weight = StringUtils.getIntFromJSon(jsonData, "weight");
			int movingObject = StringUtils.getIntFromJSon(jsonData, "movingObject");
			
			if(StringUtils.isBlank(apptoken) || userId <= 0){
				return JsonUtils.responseJson(jsonObject, ConstantUtils.PARAMETER_IS_NULL);
			}
			if(verifyAppToken(userId, apptoken)){
				return JsonUtils.responseJson(jsonObject, ConstantUtils.NO_LOGIN_IN);
			}
			Userinfo userinfo = (Userinfo) getApplication().getAttribute(String.valueOf(userId));
			if(userName!=null && userName != ""){
				userinfo.setUserName(userName);;
			}
			if(userSex > 0){
				userinfo.setUserSex(userSex);
			}
			if(height > 0){
				userinfo.setHeight(height);
			}
			if(weight > 0){
				userinfo.setWeight(weight);
			}
			if(movingObject > 0){
				userinfo.setMovingObject(movingObject);
			}
			if(birthday != null){
				userinfo.setBirthday(birthday);
			}
			userInfoService.updateUserInfo(userinfo);
			getApplication().setAttribute(String.valueOf(userId), userinfo);
			jsonObject.put("resultCode",ConstantUtils.SUCCESS);
		} catch (Exception e) {
			// TODO: handle exception
			jsonObject.put("resultCode",ConstantUtils.EEROR);
		}
		return jsonObject.toString();
	}
	/**
	 * 获取用户信息
	 * @Title:           getUserInfo
	 * @Description:     TODO
	 * @param:           @param request
	 * @param:           @return   
	 * @return:          String   
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/getuserinfo")
	public String getUserInfo(HttpServletRequest request){
		JSONObject jsonObject = new JSONObject();
		try {
			JSONObject jsonData = new JSONObject();
			jsonData = JsonUtils.requestJson(request);
			int userId = StringUtils.getIntFromJSon(jsonData, "userId");
			String apptoken = StringUtils.getStringFromJson(jsonData, "apptoken");
			
			if(userId <= 0 || StringUtils.isBlank(apptoken)){
				return JsonUtils.responseJson(jsonObject, ConstantUtils.PARAMETER_IS_NULL);
			}
			/*if(verifyAppToken(userId, apptoken)){
				return JsonUtils.responseJson(jsonObject, ConstantUtils.NO_LOGIN_IN);
			}*/
			Userinfo userinfo = userInfoService.queryUserInofById(userId);
			jsonObject.put("resultCode", ConstantUtils.SUCCESS);
			jsonObject.put("userinfo",userinfo);
		} catch (Exception e) {
			// TODO: handle exception
			jsonObject.put("resultCode", ConstantUtils.EEROR);
		}
		return jsonObject.toString();
	}
	
}
