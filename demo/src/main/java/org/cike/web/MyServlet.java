package org.cike.web;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class MyServlet {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
	public static Map getParam(HttpServletRequest request){
		Map param = new HashMap();
		Enumeration enumeration = request.getParameterNames();
		while (enumeration.hasMoreElements()) {
			// IOUtils.info(enumeration.nextElement().toString());
			Object key = enumeration.nextElement();
			Object value = request.getParameter(key.toString());
			param.put(key, value);
		}

		enumeration = request.getAttributeNames();
		while (enumeration.hasMoreElements()) {
			// IOUtils.info(enumeration.nextElement().toString());
			Object key = enumeration.nextElement();
			Object value = request.getParameter(key.toString());
			param.put(key, value);
		}
		
		return param;

	}
}
