package org.cike.database;

import java.io.File;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.Map;

import org.cike.MyBean;
import org.cike.io.IOUtils;

public class H2SQL {

	public static String ALLITEM="select * from item";
	public static String administrationNodepath="select * from administration where id like '%0000'";
	
	public static void main(String[] args) throws SQLException {
		initest();
	}
	
	public static void initest( ) throws SQLException {
	//	getSQL("all(item)");
		String sql=toCSV("/home/guest","product");
		IOUtils.info(sql);
		int rs=-1;
	//	int rs=MyJDBC.update(sql);
	//	System.out.println(sql+":"+rs);
		
		File file=new File("/home/guest/administration.csv");
		sql=fromCSV(file);
		rs=MyJDBC.update(sql);
		System.out.println(sql+":"+rs);
	}
	public static String all(String table){
		return "select * from "+table;
	}
	
//	public static String limit(int page,int rows){
//		String rs="";
//		int begin=(page-1)*rows;
//		int end=page*rows-1;
//		rs=" limit "+begin+","+end;
//		return rs;
//	}
	
	public static String limit(int page,int rows){ //小数据量？int
		int begin=(page-1)*rows;
	//	int end=begin+rows;
	//	return " limit "+begin+","+end;
		return MessageFormat.format(" limit {0},{1} ", begin,rows); //
	}
	
	
	public static String  count(String table){
		return "select count(*) from "+table;
	}
	
	public static String getSQL(String key){
		String sql="";
		try{
			sql=MyBean.getField(H2SQL.class, key);
		}catch(Exception e){ //非常量
			
		}
		return sql;
	}
	
	public static String toCSV(String path,String table){
		return MessageFormat.format("CALL CSVWRITE(''{0}/{1}.csv'', ''SELECT * FROM {1}'')",path,table);
	}
	/**
	 * show tables 查看所有表
	 * show columns from table 查看表table的表结构
	 * @param file
	 */
	public static String fromCSV(File file){
		String path=file.getAbsolutePath();
		String name=IOUtils.subStringBefore(file.getName().toUpperCase(),".CSV");
		
		return MessageFormat.format("CREATE TABLE {0} AS SELECT * FROM CSVREAD(''{1}'')",name,path);
		
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
