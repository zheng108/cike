package org.cike.domain;

import java.sql.ResultSet;
import java.text.MessageFormat;
import java.util.HashMap;

import org.cike.database.MyJDBC;
import org.cike.init.MyCache;

/**
 * 模拟业务 全国行政区 
 * 数据来源 国家统计局 http://www.stats.gov.cn/tjbz/
 * @author guest
 *
 */
public class MyChina {
	final static String DOMAIN="CHINA";
	static{
		//业务注册
		//MyCache.put(DOMAIN, MyChina.class); //别名
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static String getAdministration(HashMap param){
				
		String sql="";
		if(!param.containsKey("domain")||!DOMAIN.equals(param.get("domain").toString().toUpperCase()))
			return sql;
		String nid="";
		if(param.containsKey("nodeid")){
		nid=param.get("nodeid").toString();
		}else{
			nid="-1";
		}
		sql="select * from administration where  " +getChildren(nid) ;
		//ResultSet rs=MyJDBC.query(sql);
		//return rs;
		return sql; //toTree 参数
	
	}
	
	private static String getChildren(String id){
		String rs="";
		 int indx=id.indexOf("00");
		// rs=id.substring(0,indx)+"%"+id.substring(indx+2);
		 if(indx>-1)
		 rs=MessageFormat.format(" id like ''{0}%{1}'' and id !=''{2}''", id.substring(0,indx),id.substring(indx+2),id);
		 else if(id.equals("-1")){
			 rs=" id like '%0000'";
		 }else{
			// rs=id;
			 rs=" 1=0 ";
		 }
		 System.out.println(id);
		return rs;
		
	}
	

}
