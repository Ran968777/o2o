package com.aa.o2o.utils;

import javax.servlet.http.HttpServletRequest;

public class HttpServletRequestUtil {
	public static int getInt(HttpServletRequest request,String key) {
		try {
			return  Integer.decode(request.getParameter(key));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			return -1;
		}
	}
	
	public static long getLong(HttpServletRequest request,String key) {
		try {
			return  Long.valueOf(request.getParameter(key));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			return -1L;
		}
	}
	public static double getDouble(HttpServletRequest request,String key) {
		try {
			return  Double.valueOf(request.getParameter(key));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			return -1d;
		}
	}
	public static boolean getBoolean(HttpServletRequest request,String key) {
		try {
			return  Boolean.valueOf(request.getParameter(key));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			return false;
		}
	}
	public static String getString(HttpServletRequest request,String key) {
		try {
			String res = request.getParameter(key);
			
			if (res!=null) {
				res=res.trim();
			}
			if ("".equals(res)) {
				res = null;
				
			}
			return res;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
}
