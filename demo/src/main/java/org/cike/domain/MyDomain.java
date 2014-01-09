package org.cike.domain;

import java.util.HashMap;
import java.util.Map;

import org.app.Myjackson;
import org.cike.MyBean;
import org.cike.init.MyCache;


public class MyDomain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static void initest(){
		
	}
	
//	public static boolean isDomain(HashMap param,String DOMAIN){
//		return (param.containsKey("domain")&&DOMAIN.equals(param.get("domain").toString().toUpperCase()));
//
//	}

	/**
	 * 处理请求
	 * 返回UI/chart 组件的json数据
	 * @param param
	 * @param clazz
	 * @return
	 */
	public String execute(Map param,Class clazz) {
		String result = "";

		String module = param.get("module").toString();
		String domain = param.get("domain").toString().toUpperCase();
		String func = param.get("func").toString();

	
	//	String method = "to" + module;
		String method =getFunc(module);
		MyCache cache=MyCache.getInstance();
		Object args = MyBean.execute((Class) cache.get(domain), func, param); // rs不通用
		args = MyBean.execute(clazz, method, args);
		result = Myjackson.toJSON(args);
		return result;
	}
	
	
	public String getFunc(String func){
		return "to"+func;
	}
}
