package org.cike.ui;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.app.Myjackson;
import org.cike.IPorts;
import org.cike.MyBean;
import org.cike.MyCache;
import org.cike.MyVisit;
import org.cike.data.MyChildrenNode;
import org.cike.database.DAO;
import org.cike.database.H2SQL;
import org.cike.database.MyJDBC;
import org.cike.init.MyDefault;
import org.cike.io.IOUtils;

public class MyEasyUI {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		initest();
	}

	public static MyEasyUI initest() {
		MyEasyUI eUI = new MyEasyUI();

		MyJDBC jdbc = new MyJDBC();
		ResultSet rs = jdbc.all("item");
		String temp = eUI.toTable(jdbc.all("item"), 0);
		IOUtils.info(temp);
		
		temp=eUI.toTable(H2SQL.ALLITEM, 1, 10);
		IOUtils.info(temp);
		
		IOUtils.info(getChildren("110000"));
		
		temp=toTree("440000");
		IOUtils.info(temp);
		return eUI;
	}

	///表格
	
	/**
	 * 
	 * @param query
	 * @param page
	 * @param rows
	 * @return
	 */
	public static String toTable(String query, Integer page, Integer rows) {
	
		DAO dao=MyDefault.getDAO();
		String from=IOUtils.subStringAfter(query, "from");
		long count=(Long) MyBean.execute(dao, "count", from);
		String sql=query+TablePort.limit(page, rows);
		ResultSet rs=(ResultSet)MyBean.execute(dao, "query", sql);
	
		
		return toTable(rs,count);
	}

	private static String toTable(ResultSet rs, long total) {

		final String key = "field";
		final Gridata gridata = new Gridata();
		TablePort port = new TablePort() {

			@Override
			public void addColumn(String name) {
				// TODO Auto-generated method stub
				Map map = new HashMap();
				map.put(key, name);
				map.put("title", name);
				map.put("align", "center"); // 默认
				gridata.columns.add(map);
			}

			@Override
			public void addRows(Object[] rows) {
				// TODO Auto-generated method stub
				Map map = new HashMap();
				for (int i = 0; i < rows.length; i++) {
					map.put(gridata.columns.get(i).get(key), rows[i]);
				}
				gridata.rows.add(map);

			}

		}.toTableSet(rs);
		;
		if (total == 0)// bug
			gridata.total = gridata.rows.size();
		else
			gridata.total = total;
		return Myjackson.toJSON(gridata);
	}
	
	///tree
	public static String toTree(String nodeid){
		
			Map map= toTreeviews(nodeid);
		
		String cid=getChildren(nodeid);
		if(cid.equals(nodeid))return "[]";
		String sql="select * from administration where "+ cid;
		DAO dao=MyDefault.getDAO();
		ResultSet rs=(ResultSet)MyBean.execute(dao, "query", sql);
		
		if(true){
			
			return Myjackson.toJSON(map.values());
		}
		
		return toTree(rs);
	}
	
	static String toTree(ResultSet rs){
		List list=new ArrayList(); 
		
		
		try {
		  // rs.beforeFirst();
         //   MyChildrenNode cn=new MyChildrenNode();
			while (rs.next()) {
				Treeview tv=new Treeview();
				tv.id=rs.getString(1);
				tv.text=rs.getString(2);
				//attributes
				list.add(tv);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//toTree(list);
		return Myjackson.toJSON(list);//待定
	}
	
	public static Map toTreeviews(String nid){
		Map map=new HashMap();
		
	
			String sql="select * from administration where  " +getChildren(nid) ;
			ResultSet rs=MyJDBC.query(sql);
		
			try {
				  // rs.beforeFirst();
		      
					while (rs.next()) {
						//Treeview tv=new Treeview();
						//map.put(tv.id, new MyChildrenNode(tv));
						
						TreeView tv=new TreeView();
						tv.id=rs.getString(1);
						tv.text=rs.getString(2);
					    map.put(tv.id, tv);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(map.size()>1)
			toTreeviews(map);
			return map;
		
	}
	
	public static void toTreeviews(Map map){
		MyVisit.visit(map, new IPorts (){

			public Object execute(Object... obj) {
				// TODO Auto-generated method stub
				String key=obj[0].toString();
			//	MyChildrenNode cnode=(MyChildrenNode) obj[1];
			//	String id=cnode.getParam("id").toString();
				TreeView value=(TreeView)obj[1];
				String id=MyBean.getField(obj[1], "id");
				
				Map child=toTreeviews(id);
				if(child!=null&&child.size()>0){
					value.state="open";
				value.addChild(child);
				toTreeviews(child);
				}
				return null;
			}
			
		});
	}
	
	public static String getChildren(String id){
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
