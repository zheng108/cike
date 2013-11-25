package org.cike;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.cike.database.H2SQL;

public class MyBean {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		initest();
	}
	
	public static void initest(){
		getField(H2SQL.class,"ALLITEM");
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
	
	public static Object execute(Class type, String method, Object... args) {

		Class[] parameterTypes = null;
		if (args.length > 0) {
			parameterTypes = new Class[args.length];
			for (int i = 0; i < args.length; i++) {
				Class clazz=getType(args[i]); //bug
				parameterTypes[i] = clazz;
			}
		}
		try {
			
			Method func = type.getMethod(method, parameterTypes); //bug
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
	
	private static Class getType(Object obj){
		Class type=obj.getClass();
		Class[] interfaces=type.getInterfaces();
		
		if(interfaces!=null&&interfaces.length>0){
			type=interfaces[0];
		}
		
//		if(type.getSuperclass() instanceof Object){
//			
//		}else{
//			type=type.getSuperclass();
//		}
		return type;
	}
	
	public static String getField(Class clazz,String name){
		String rs="";
		try {
			Field field=clazz.getField(name);
			rs=field.toGenericString();
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

}
