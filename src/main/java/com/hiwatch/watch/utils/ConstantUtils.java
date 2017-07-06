package com.hiwatch.watch.utils;

public class ConstantUtils {

	public final static String URL = "https://eco.taobao.com/router/rest";
	
	public final static String APPKEY = "23632518";
	
	public final static String SECRET = "41ad5356a0f7909afe2620b98bd151a7";
	
	public final static String TEMPLATEID = "SMS_69660001";
	
	public static final int SUCCESS =1;
	
	public static final int EEROR = -1;
	
	public static final int PARAMETER_IS_NULL = 1100;// 非空参数为空
	public static final int NO_LOGIN_IN = 1101;// token失效
	public static final int PARAMETER_VALUES_ERROR = 1102;// 参数值有误
	public static final int VERIFY_CODE_ERROR = 1103;//验证码错误
	public static final int USER_HAVE_REGIST = 1104;//用户已注册
	public static final int VERIFY_CODE_EXPIRED = 1105;//验证码已过期
	public static final int ACCOUNT_OR_PWD_ERROR = 1106;//账号或密码错误
	
	public static final int USER_OR_DEVICE_BOUND =1107;//用户或设备已被绑定
	public static final int USER_OR_DEVICE_UNBOUND = 1108;//用户未绑定此设备
	public static final Object USER_UNBOUND_DEVICE = 1109;//用户未绑定设备
	
	//用户图像保存路径
	public static final String TOMCAT_URL ="/alidata/robot/watch-tomcat-8083/webapps" ;
	//用户图像
	public static final String USUE_POTHO_URL = "/img/";
}
