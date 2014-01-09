package org.cike.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.cike.MyBean;
import org.cike.MyVisit;
import org.cike.Ports;
import org.cike.init.MyCache;
import org.cike.io.IOUtils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DAO {
	
	public static void main(String[] args) throws SQLException {
		initest();
	}
	
	public static void initest( ) throws SQLException {
		String sql=H2SQL.all("item");
		query(sql);
		List list=queryList(sql);
		for(Object obj : list){
			System.out.println(obj);
		}
	}
	
	public static ResultSet query(String sql) throws SQLException{ //默认
		return queryResultSet(sql);
	}
	
	
	public static ResultSet queryResultSet(String sql ) throws SQLException{
		MyCache cache=MyCache.getInstance();
		cache.put("DATASOURCE", new ComboPooledDataSource()); //使用c3p0
		Runnable dao=(Runnable)cache.get("DAO");
		ResultSet rs=(ResultSet)MyBean.execute(dao, "query", sql);
	//	rs.last();
	//	IOUtils.info(DAO.class,rs.getRow());
		return rs;

	}
	
	/**
	 * 第一行为列
	 * 从第二行开始为Object［］数据
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public static List queryList(String sql) throws SQLException{
		final List list=new ArrayList();
		final List column=new ArrayList();
		list.add(column);
		ResultSet rs=queryResultSet(sql);
		
		
		new TablePort(){

			@Override
			public void addColumn(String name) {
				// TODO Auto-generated method stub
				column.add(name);
			}

			@Override
			public void addRows(Object[] rows) {
				// TODO Auto-generated method stub
				list.add(rows);
			}
			
		}.toTableSet(rs);
		
		return list;
	}
	
	public static List queryListMap(String sql) throws SQLException{
		
		ResultSet rs=queryResultSet(sql);
		
		
		return MyTableSet.format(rs);
	}
	
	
	/**
	 * 自定义结果集 持久化
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public static MyTableSet queryTableSet(String sql) throws SQLException{
		ResultSet rs=queryResultSet(sql);
		return new MyTableSet(rs);

	}
	
}
