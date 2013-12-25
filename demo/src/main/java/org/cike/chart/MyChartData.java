package org.cike.chart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyChartData {

	List<Map> data=new ArrayList();
	Map options=new HashMap();
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public String getType(){
		String type="";
		if(options!=null&&options.containsKey("type")){
			type=options.get("type").toString();
		}
				
		return type;
	}
	
	public List<Map> getData(){
		return data;
	}

}
