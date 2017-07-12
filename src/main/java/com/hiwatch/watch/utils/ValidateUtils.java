package com.hiwatch.watch.utils;

import java.util.regex.Pattern;




public class ValidateUtils {

	/*public static boolean isPassword(String pwd){
		
	}*/
	public static boolean isPassword(String pwd) {
		if (ValidateUtils.isInteger(pwd)) {// 是否全部为数字
			return false;// 全部为数字
		} else {
			if (ValidateUtils.isLettersOnly(pwd)) {
				return false;// 全部为字母
			} else {
				if (ValidateUtils.isAlphanumeric1(pwd)) {
					return true;// 正常数据
				} else {
					return false;// 包含了非法数据
				}
			}
		}
	}
	public static boolean isNumeric(String str) {
		if (StringUtils.isBlank(str)) {
			return false;
		} else {
			return str.matches("\\d*");
		}
	}
	public static boolean isInteger(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		return pattern.matcher(str).matches();
	}
	public static boolean isLettersOnly(String str) {
		Pattern pattern = Pattern.compile("^[A-Za-z]+$");
		return pattern.matcher(str).matches();
	}
	public static boolean isAlphanumeric1(String str) {
		Pattern pattern = Pattern.compile("^[A-Za-z0-9]{6,16}$");
		return pattern.matcher(str).matches();
	}
	
	public static boolean isEmail(String str) {
		Pattern pattern = Pattern.compile("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
		return pattern.matcher(str).matches();
	}
	public static boolean isMobile(String mobile) {
		Pattern pattern = Pattern.compile("^1[3|4|5|7|8][0-9]{9}$");
		return pattern.matcher(mobile).matches();
	}
	public static boolean isQQ(String qq){
		return false;
	}
}
