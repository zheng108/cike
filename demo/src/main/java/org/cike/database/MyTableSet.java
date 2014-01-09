package org.cike.database;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyTableSet {
//	private ResultSet rs; //待定
	private List<String> columns=new ArrayList();
	private Map<String,Object> index=new HashMap(); //value待定
	//List<Map>  List<List> List<Object[]>
	private List<Object[]> data=new ArrayList();
	TablePort port=new TablePort(){

		public void addColumn(String name) {
			// TODO Auto-generated method stub
			 if(!columns.contains(name))
				   columns.add(name);
		}

		public void addRows(Object[] rows) {
			// TODO Auto-generated method stub
			data.add(rows);
		}
		
	};
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	MyTableSet(){
		
		
	}
	
	MyTableSet(ResultSet rs){
		port.toTableSet(rs);
	}
	
	
	public static MyTableSet initest(){
		MyTableSet ts=new MyTableSet();
		return ts;
	}

	public List<Object[]> getData() {
		return data;
	}
	/////工具
	
	/**
	 * 根据列名返回值 可switch优化
	 * @param rs
	 * @param name
	 * @return
	 * @throws SQLException
	 */
	private static Object getColumn(ResultSet rs,String name) throws SQLException{
		return rs.getObject(name); //待优化
	}
	
	///工具
	public static List format(ResultSet rs) throws SQLException{
		List list=new ArrayList();
		Object[] column=getColumn(rs);
		while(rs.next()){
			Map map=new HashMap();
		   for(Object obj : column){
			   map.put(obj, getColumn(rs,obj.toString()));
		   }
		   list.add(map);
		}
		return list;
	}
	
	public static Object[] getColumn(ResultSet rs) throws SQLException{
	ResultSetMetaData rsmd = rs.getMetaData();
	int size = rsmd.getColumnCount();
	Object[] column=new Object[size];
	for (int i = 0; i < size; i++) {
		column[i]=rsmd.getColumnName(i + 1);
	}
	return column;
	}
	


}
