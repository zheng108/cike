package org.cike.database;

import java.util.Map;

public class H2SQL {

	public static String ALLITEM="select * from item";
	
	public static String all(String table){
		return "select * from "+table;
	}
	
	public static String limit(int page,int rows){
		String rs="";
		int begin=(page-1)*rows;
		int end=page*rows-1;
		rs=" limit "+begin+","+end;
		return rs;
	}
	
	public static String  count(String table){
		return "select count(*) from "+table;
	}
	
//	public static String selectTableWhere(Object[] select,String table,Map where){
//		StringBuffer sb=new StringBuffer();
//		//String sel=select[0].toString();
//		sb.append("select ").append(select[0]);
//		for(int i=1;i<select.length;i++){
//			sb.append(","+select[i]);
//		}
//		
//		String from=String.format(" from %s where ", table);
//		sb.append(from);
//		
//		
//		return sb.toString();
//	}
}
