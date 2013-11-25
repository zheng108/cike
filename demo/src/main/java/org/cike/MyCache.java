package org.cike;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
/**
 *  最简单的内存缓存
 * @author guest
 *
 */
public class MyCache {

	static Map<String,Object> cache=new HashMap();
 	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static Object get(String key,Ports port){
		Object rs=null;
		if(cache.containsKey(key)){
			rs=cache.get(key);
		}else{
			rs=port.execute(key);
		}
		return rs;
	}
	
	public static Object get(String key){
		return cache.get(key);
	}
	
	public static void put(String key,Object value){
		cache.put(key, value);
	}

}
