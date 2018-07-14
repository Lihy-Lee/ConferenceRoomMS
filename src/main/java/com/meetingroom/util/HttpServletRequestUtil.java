package com.meetingroom.util;

import javax.servlet.http.HttpServletRequest;

/**
 * 负责处理HttpServletRequest请求的参数格式转换
 * 
 * @author LHY
 *
 */
public class HttpServletRequestUtil {
	// 转化为整型参数
	public static int getInt(HttpServletRequest request, String key) {
		try {
			return Integer.decode(request.getParameter(key));
		} catch (Exception e) {
			return -1;
		}
	}

	// 转化为长整型
	public static long getLong(HttpServletRequest request, String key) {
		try {
			return Long.valueOf(request.getParameter(key));
		} catch (Exception e) {
			return -1;
		}
	}

	// 转化为double参数
	public static double getDouble(HttpServletRequest request, String key) {
		try {

			return Double.valueOf(request.getParameter(key));
		} catch (Exception e) {
			return -1;
		}
	}

	public static boolean getBoolean(HttpServletRequest request, String key) {
		try {
			// 转化为长整型
			return Boolean.valueOf(request.getParameter(key));
		} catch (Exception e) {
			return false;
		}
	}

	public static String getString(HttpServletRequest request, String key) {
		try {
			String result = request.getParameter(key);
			if (result != null) {
				result = result.trim();
			}
			if ("".equals(result)) {
				result = null;
			}
			return result;
		} catch (Exception e) {
			return null;
		}
	}

}
