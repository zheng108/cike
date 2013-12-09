package org.app;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.cike.MyVisit;
import org.cike.init.MyDefault;
import org.cike.io.IOUtils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class MyDBUtils {

	private DataSource ds;
	
	MyDBUtils(){
	//	ds=new ComboPooledDataSource();
		ds=MyDefault.getDataSource();
	}
	
	public MyDBUtils(DataSource ds){
		this.ds=ds;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		initest();
	}
	
	public static void initest(){
	  MyDBUtils  utils=new MyDBUtils();//new MyDataSource()
	  String sql="select * from item";
	  List<Map<String, Object>> listmap=utils.query(sql);
	  
	  IOUtils.info(listmap.size()+"");
	 // MyShow.listMap(listmap);
	}
	
	public List<Map<String, Object>> query(String sql){
		return queryList(sql,null);
	}
	
	public Map queryMap(String sql,Object[] params){
		Map rs=null;
		QueryRunner run=new QueryRunner(ds);
			try {
			 rs=run.query(sql, new MapHandler(),params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public List<Map<String,Object>> queryList(String sql,Object[] params){
		List<Map<String, Object>> rs=null;
		QueryRunner run=new QueryRunner(ds);
			try {
			 rs=run.query(sql, new MapListHandler(),params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	

}
