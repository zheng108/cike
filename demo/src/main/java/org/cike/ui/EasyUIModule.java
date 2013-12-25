package org.cike.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.cike.io.IOUtils;



public class EasyUIModule {
    //或直接定义为Map
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		initest();
	}
	
	public static void initest(){
		IOUtils.info(getTable("UItable"));
	}
	
	public static String getTable(String tID){
		return String.format("<table id='%s' class='easyui-datagrid'></table>",tID);
	}
}

class Gridata {
	public long total = 0;
	public List<Map> columns = new ArrayList<Map>();
	public List<Map> rows = new ArrayList();
}

 class Treeview{
	public String id;
	public String text;
	public String state="closed";//open
	public Map attributes;
//	public List<Treeview> children ;
}
 
 class TreeView{
	public String id;
	public String text;
	public String state;//="closed";//open
	public Map attributes;
	public List<TreeView> children ;
	
	public void addChild(TreeView node){
		if(children==null){
			children=new ArrayList();
		}
		children.add(node);
	
	}
	
	public void addChild(Map<String,TreeView> nodes){
		if(children==null){
			children=new ArrayList();
		}
		if(nodes==null)return;
		for(TreeView obj:nodes.values()){
		children.add(obj);
		}
	}
}
