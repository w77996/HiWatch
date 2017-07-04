package com.hiwatch.watch.utils;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSONObject;

/**
 * Description:Json解析工具类
 * 
 * @author w77996
 * @date 2017年7月4日 下午9:51:50
 */
public class JsonUtils {

	private static final Log LOG = LogFactory.getLog(JsonUtils.class);

	private JsonUtils() {

	}

	/**
	 * 
	 * @Title: requestJson
	 * @Description: TODO
	 * @param: @param request
	 * @param: @return
	 * @return: JSONObject
	 * @throws
	 */
	public static JSONObject requestJson(HttpServletRequest request) {
		StringBuffer sBuffer = new StringBuffer();
		String line = null;
		JSONObject jsonObject = null;
		try {
			BufferedReader bReader = request.getReader();
			while ((line = bReader.readLine()) != null) {
				sBuffer.append(line);
			}
			bReader.close();
			jsonObject = JSONObject.parseObject(sBuffer.toString());
		} catch (IOException e) {
			// e.printStackTrace();
			LOG.error(e);
		}
		return jsonObject;
	}

	/**
	 * 
	 * @Title: responseJson
	 * @Description: TODO
	 * @param: @param response
	 * @param: @param object
	 * @return: void
	 * @throws
	 */
	public static void responseJson(HttpServletResponse response, Object object) {
		Object obj = JSONObject.toJSON(object);

		try {
			response.getWriter().write(obj.toString());
		} catch (IOException e) {
			// e.printStackTrace();
			LOG.error(e);
		}
	}

	/**
	 * 
	 * @Title: responseJson
	 * @Description: TODO
	 * @param: @param jsonObject
	 * @param: @param code
	 * @param: @return
	 * @return: String
	 * @throws
	 */
	public static String responseJson(JSONObject jsonObject, int code) {
		jsonObject.put("resultCode", code);
		return jsonObject.toJSONString();
	}
}
