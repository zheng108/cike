package org.cike.database;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.cike.ui.TablePort;

public class MyTableSet {
//	private ResultSet rs; //待定
	private List<String> columns=new ArrayList();
	private Map<String,Object> index=new HashMap(); //value待定
	//List<Map>  List<List> List<Object[]>
	private List<Object[]> data=new ArrayList();
	TablePort port;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	MyTableSet(){
		port=new TablePort(){

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
		
	}
	
//	MyTableSet(ResultSet rs){
//		this.rs=rs;
//	}
//	
	
	public static MyTableSet initest(){
		MyTableSet ts=new MyTableSet();
		return ts;
	}
	
	public void query(String sql){
		
	}
	


}
