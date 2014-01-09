package org.cike.init;

import java.lang.reflect.InvocationHandler;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.cike.MyBean;
import org.cike.Ports;
import org.cike.database.MyDataSource;
import org.cike.database.MyJDBC;

import com.mchange.v2.c3p0.ComboPooledDataSource;
/**
 *  最简单的内存缓存
 *  单例 
 *  ＋ 动态代理 （待定）
 * @author guest
 *
 */
public class MyCache {

	private Map<String,Object> cache=new HashMap();
	private static MyCache instance=new MyCache();
	
	
 	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
 	
 	private MyCache(){
 		cache.put("DATASOURCE", new MyDataSource());
 		cache.put("DAO", new MyJDBC());
 		
 	} //单例
 	
 	/**
 	 * 代理 ？
 	 * 第一次使用MyCache.getInstance前可显示调用init
 	 * @param handler
 	 */
 	public static void init(InvocationHandler handler){
 	 		instance=MyBean.getProxy(MyCache.class, handler);
 	}
 	
 	public static void init(Ports port){
 		Map map=(Map)port.execute(null);
 		instance.cache.putAll(map);
 	}
 	
 	public static MyCache getInstance(){
 		if(instance==null){
 			instance=new MyCache();
 		}
 			return instance;
 	}

	public  Object get(String key,Ports port){
		Object rs=null;
		if(cache.containsKey(key)){
			rs=cache.get(key);
		}else{
			rs=port.execute(key);
		}
		return rs;
	}
	
	public  Object get(String key){
		return cache.get(key);
	}
	
	public void put(String key,Object value){
		cache.put(key, value);
	}
	
	public boolean has(String key){
		return cache.containsKey(key);
	}

}
