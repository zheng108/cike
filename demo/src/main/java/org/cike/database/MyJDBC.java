package org.cike.database;
/**
 * 处理sql 
 * ResultSet 转换
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.cike.IPorts;
import org.cike.MyVisit;
import org.cike.Ports;
import org.cike.init.MyCache;

import org.cike.io.IOUtils;

public class MyJDBC implements Runnable {

	//static DataSource ds=new MyDataSource();
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		initest();
	}

	public static MyJDBC initest() {
		MyJDBC db = new MyJDBC();

		db.query(H2SQL.ALLITEM);
		return db;
	}

	public MyJDBC() {
	//	ds = MyDefault.getDataSource();
	}

	public static ResultSet query(String sql) {
		System.out.println(sql);
		if(sql.equals("")) return null;
		MyCache cache=MyCache.getInstance();
		DataSource ds = (DataSource)cache.get("DATASOURCE");
		PreparedStatement pst;
		try {
			Connection connect = ds.getConnection();
			pst = connect.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);// ,ResultSet.TYPE_SCROLL_SENSITIVE,
												// ResultSet.CONCUR_READ_ONLY
			ResultSet rs = pst.executeQuery();
			// pst.close();
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	
	public static int update(String sql) throws SQLException {
		System.out.println(sql);
		
		PreparedStatement pst;
		int rs=-1;
		MyCache cache=MyCache.getInstance();
		DataSource ds = (DataSource)cache.get("DATASOURCE");
			Connection connect = ds.getConnection();
			pst = connect.prepareStatement(sql);
			 rs= pst.executeUpdate();
			 pst.close();
			
		

		return rs;
	}

	public static ResultSet all(String table) {
		String sql = "select * from " + table;
		return query(sql);
	}

	public static long count(String table) {
		String sql = H2SQL.count(table);
		ResultSet rs = query(sql);
		long count = 0;
		try {
			if (rs.next()) {
				count = rs.getLong(1);
			}

			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	
	

	public static void commit(Ports port) throws SQLException {
		Connection connect = null;
		MyCache cache=MyCache.getInstance();
		DataSource ds = (DataSource)cache.get("DATASOURCE");
		try {
			connect = ds.getConnection();
			connect.setAutoCommit(false);
			port.execute(connect);
			connect.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			connect.rollback();
		}
	}
	
	
	/*
	 * public static ResultSet query(Map map){ String sql=""; final StringBuffer
	 * sb=new StringBuffer();
	 * 
	 * String table="";
	 * sb.append(String.format("select * from %s where ",table));
	 * 
	 * 
	 * MyVisit.visit(map, new IPorts(){
	 * 
	 * public Object execute(Object... obj) { // TODO Auto-generated method stub
	 * 
	 * String key=obj[0].toString(); Object val=obj[1];
	 * 
	 * if(key.equals("class")){ // sb.insert(0,
	 * "select * from "+((Class)val).getSimpleName()) }
	 * 
	 * return null; }
	 * 
	 * });
	 * 
	 * return query(sql); }
	 */

	public void run() {
		// TODO Auto-generated method stub
		
	}
}
