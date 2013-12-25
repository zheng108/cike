package org.cike;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import javax.activation.FileDataSource;

import org.cike.data.MyChildrenNode;
import org.cike.data.Test;
import org.cike.database.H2SQL;
import org.cike.domain.MyChina;
import org.cike.init.MyCache;
import org.cike.io.IOUtils;
import org.cike.ui.EasyUIModule;



public class MyBean {

	static Map map=new HashMap();
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		initest();
	}
	
	public static void initest(){
		//getField(H2SQL.class,"ALLITEM");
		Test t=new Test();
		String rs="";
		MyChildrenNode cn=new MyChildrenNode(t);
		rs=getField(t,"id");
		
		rs=cn.getParam("name").toString();
		
		execute(MyChina.class,"getAdimsistration",new HashMap());
		IOUtils.info(rs);
	}
	
	public static Object execute(Object obj, String method, Object... args) {

		Class[] parameterTypes = null;
		if (args.length > 0) {
			parameterTypes = new Class[args.length];
			for (int i = 0; i < args.length; i++) {
				parameterTypes[i] = args[i].getClass();
			}
		}
		try {
			Class type = obj.getClass();
			Method func = type.getMethod(method, parameterTypes);
			
			//cache
			map.put(func.getName(),func);
			
			return func.invoke(obj, args);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 执行 静态无参 方法
	 * @param type
	 * @param method
	 * @return
	 */
	public static Object execute(Class type,String method){
		Object rs=null;
		
		try {
			Method func= type.getMethod(method, null);
			rs=func.invoke(type, null);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return rs;
	}
	
	public static Object execute(Class type, String method, Object... args) {

		Class[] parameterTypes = null;
		if (args.length > 0) {
			parameterTypes = new Class[args.length];
			for (int i = 0; i < args.length; i++) {
			//	Class clazz=getType(args[i]); //bug
				Class clazz=args[i].getClass();
				parameterTypes[i] = clazz;
			}
		}
		try {
			String name=type.getName()+"."+method;
			Method func=null;
			MyCache cache=MyCache.getInstance();
			if(cache.has(name)){
				func=(Method)cache.get(name);
			}else{
			
			 func= type.getMethod(method, parameterTypes); //bug
			 cache.put(name, func);
			}
			return func.invoke(type, args);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

	
	/**
	 * 获取 静态常量 
	 * 代定
	 * @param clazz
	 * @param name
	 * @return
	 */
	public static String getField(Class clazz,String name){
		String rs="";
		try {
			Field field=clazz.getField(name);
			//rs=field.toGenericString();
			rs=field.get(null).toString();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public static String getField(Object obj,String name){
		String rs="";
		try {
			Field field=obj.getClass().getDeclaredField(name);
			field.setAccessible(true);
			//rs=field.toGenericString();
			rs=field.get(obj).toString();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	/**
	 * 需 set/get
	 * @param obj
	 * @param val
	 * @return
	 */
	public static Object getProperty(Object obj, String val) {
		Object rs=null;
		try {
			PropertyDescriptor pd = new PropertyDescriptor(val, obj.getClass());
			// pd.getWriteMethod().invoke(obj, val);
			//setProperty(pd, obj, val);
			rs=pd.getReadMethod().invoke(obj, null);
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rs;
	}
	
	public static <T> T getProxy(T target,InvocationHandler handler){
		return (T)Proxy.newProxyInstance(target.getClass().getClassLoader(),  
                target.getClass().getInterfaces(), handler); 
	}
	
	public static <T> T getProxy(Class<T> target,InvocationHandler handler){
		return (T)Proxy.newProxyInstance(target.getClassLoader(),  
                target.getInterfaces(), handler); 
	}
	
	public static void load(String name){
		ClassLoader loader = Thread.currentThread()
				.getContextClassLoader();
		
		try {
			loader.loadClass(name).newInstance();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
