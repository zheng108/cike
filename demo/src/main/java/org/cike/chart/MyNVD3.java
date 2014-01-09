package org.cike.chart;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.app.Myjackson;
import org.cike.database.DAO;
import org.cike.domain.MyInvoice;
import org.cike.io.IOUtils;

public class MyNVD3 {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub

		initest();
	}
	
	public static void initest( ) throws SQLException{
		String data[]=toPie(MyInvoice.AreaInfo);
		IOUtils.info(MyNVD3.class, data.length);
	}

	public static String[] toPie(String sql) throws SQLException{
		List<Map> list=DAO.queryListMap(sql);
		// 可直接返回 string
		//格式转换 
		String []rs=new String[3];
		if(list.size()>0){
//			Set set=list.get(0).keySet();
//			Object[] key=set.toArray();
//		rs[0]=key[0].toString();	
//		rs[1]=key[1].toString();	
			
			Map map=list.get(0);
			Object []key=map.keySet().toArray();
			if(map.get(key[0]) instanceof Number){
				rs[1]=key[0].toString();
				rs[0]=key[1].toString();
			}else{
				rs[0]=key[0].toString();
				rs[1]=key[1].toString();
			}
			
			
		rs[2]=Myjackson.toJSON(list);
		}
		return rs;
	}
}
