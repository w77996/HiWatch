package com.hiwatch.watch.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hiwatch.watch.entity.MailParam;
import com.hiwatch.watch.entity.Userinfo;
import com.hiwatch.watch.entity.Verfiycode;
import com.hiwatch.watch.mail.MailBiz;
import com.hiwatch.watch.service.UserInfoService;
import com.hiwatch.watch.service.VerifyCodeService;
import com.hiwatch.watch.utils.ConstantUtils;
import com.hiwatch.watch.utils.JsonUtils;
import com.hiwatch.watch.utils.StringUtils;
import com.hiwatch.watch.utils.ValidateUtils;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.BizResult;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;


/**
 * Description:登录
 * @author w77996
 * @date 2017年7月4日 下午2:53:56
 */
@Controller
@RequestMapping(value="/login")
public class LoginController extends BaseController{
	
	private static final Logger LOG = Logger.getLogger(LoginController.class);
	
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private VerifyCodeService verifyCodeService;
	@Autowired
	private MailBiz mailBiz;
	/**
	 * 注册模块
	 * @Title:           reigst
	 * @Description:     TODO
	 * @param:           @param request
	 * @param:           @return   
	 * @return:          String   
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/regist")
	public String reigst(HttpServletRequest request){
		JSONObject json = new JSONObject();
		try {
			JSONObject jsonData = JsonUtils.requestJson(request);
			String userAccount = StringUtils.getStringFromJson(jsonData, "account");
			String codeString = StringUtils.getStringFromJson(jsonData, "code");
			String pwdString = StringUtils.getStringFromJson(jsonData, "pwd");
			//判断账户是否是Email,Moblie
			if(!(ValidateUtils.isEmail(userAccount)||ValidateUtils.isMobile(userAccount)||StringUtils.isEmpty(userAccount)||StringUtils.isEmpty(pwdString))){
				return JsonUtils.responseJson(json, ConstantUtils.PARAMETER_IS_NULL);
			}
			//判断密码是否符合规范
			if(!ValidateUtils.isPassword(pwdString)){
				return JsonUtils.responseJson(json, ConstantUtils.PARAMETER_VALUES_ERROR);
			}
			//查询用户是否已经注册过
			Userinfo userinfo = userInfoService.queryUserInfoByMoBile(userAccount);
			if(userinfo!=null){
				return JsonUtils.responseJson(json, ConstantUtils.USER_HAVE_REGIST);
			}
			//查询是否存在验证码
			Map<String, Object> paramMap = new HashMap<String,Object>();
			paramMap.put("userAcctount", userAccount);
			paramMap.put("type", "regist");
			Verfiycode verfiycode = verifyCodeService.queryVerfiycode(paramMap);
			if(verfiycode == null){
				return JsonUtils.responseJson(json, ConstantUtils.VERIFY_CODE_ERROR);
			}
			if(!verfiycode.getCode().equals(codeString)){
				return JsonUtils.responseJson(json, ConstantUtils.VERIFY_CODE_ERROR);
			}
			if(verfiycode.getStatus() != 1){
				return JsonUtils.responseJson(json,ConstantUtils.VERIFY_CODE_EXPIRED);
			}
			//插入用户数据
			userinfo = new Userinfo();
			userinfo.setUserAccount(userAccount);
			userinfo.setUserPwd(pwdString);
			userinfo.setCreateTime(new Date());
			userinfo.setPhoto("/img/ic_launcher.png");
			verfiycode.setStatus(2);
			userInfoService.addUserInfo(userinfo, verfiycode);
			json.put("resultCode", ConstantUtils.SUCCESS);
		} catch (Exception e) {
			// TODO: handle exception
			json.put("resultCode", ConstantUtils.EEROR);
			LOG.error("reigst error"+e);
		}
		return json.toString();
	}
	
	@RequestMapping(value="/verify")
	public @ResponseBody String verifyCodeString(HttpServletRequest request){
		JSONObject jsonObject = new JSONObject();
		try {
			JSONObject jsonData = JsonUtils.requestJson(request);
			
			String action = StringUtils.getStringFromJson(jsonData, "action");
			String userAccount = StringUtils.getStringFromJson(jsonData, "account");
			System.out.println(action + " "+userAccount);
			if(StringUtils.isEmpty(action) || StringUtils.isEmpty(userAccount)){
				return JsonUtils.responseJson(jsonObject, ConstantUtils.PARAMETER_IS_NULL);
			}
			if(ValidateUtils.isMobile(userAccount)){
				if(!action.equals("regist") && !action.equals("forget")){
					return JsonUtils.responseJson(jsonData, ConstantUtils.PARAMETER_VALUES_ERROR);
				}
				System.out.println("sendmobilecode");
				sendMobileCode(jsonData, action, userAccount);
			}else if(ValidateUtils.isEmail(userAccount)){
				if(!action.equals("regist") && !action.equals("forget")){
					return JsonUtils.responseJson(jsonObject, ConstantUtils.PARAMETER_VALUES_ERROR);
				}
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	/**
	 * 发送手机验证码
	 * @Title:           sendMobileCode
	 * @Description:     TODO
	 * @param:           @param json
	 * @param:           @param action
	 * @param:           @param mobile
	 * @param:           @throws ApiException   
	 * @return:          void   
	 * @throws
	 */
	public void sendMobileCode(JSONObject json,String action,String mobile) throws ApiException{
		String numString = getPhoneCode();
		TaobaoClient client = new DefaultTaobaoClient(ConstantUtils.URL, ConstantUtils.APPKEY, ConstantUtils.SECRET);
		AlibabaAliqinFcSmsNumSendRequest request = new AlibabaAliqinFcSmsNumSendRequest();
		System.out.println("request int");
		request.setExtend("123456");
		request.setSmsType("normal");
		request.setSmsFreeSignName("蓝牙手表");
		request.setSmsParamString("{\"num\":\""+numString+"\"}");
		
		request.setRecNum(mobile);
		request.setSmsTemplateCode(ConstantUtils.TEMPLATEID);
		System.out.println("response int");
		AlibabaAliqinFcSmsNumSendResponse reponse = client.execute(request);
		System.out.println("response getresult");
		BizResult result = reponse.getResult();
		System.out.println("result:"+result.getSuccess());
		
		if(result.getSuccess()){
			Verfiycode verfiycode = new Verfiycode();
			verfiycode.setUserAccount(mobile);
			verfiycode.setCode(numString);
			verfiycode.setType(action);
			verfiycode.setCreateTime(new Date());
			verifyCodeService.addVerifyCode(verfiycode);
			json.put("resultCode", ConstantUtils.SUCCESS);
		}else{
			json.put("resultCode",ConstantUtils.EEROR);
		}
		
	}
	/**
	 * 发送邮件验证码
	 * @Title:           sendEmailCode
	 * @Description:     TODO
	 * @param:           @param action
	 * @param:           @param email   
	 * @return:          void   
	 * @throws
	 */
	public void sendEmailCode(String action,String email){
		String code = getPhoneCode();
		MailParam mailParam = new MailParam();
		mailParam.setTo(email);
		mailParam.setSubject("蓝牙手环");
		mailParam.setContent("验证码：" +code);
		Verfiycode verfiycode = new Verfiycode();
		verfiycode.setCode(code);
		verfiycode.setType(action);
		verfiycode.setUserAccount(email);
		verfiycode.setCreateTime(new Date());
		verifyCodeService.addVerifyCode(verfiycode);
	}
	
	private String getPhoneCode(){
		Random random = new Random();
		int result = random.nextInt(9999);
		return "000000".substring((result+"").length())+result;
	}
	
	
}
