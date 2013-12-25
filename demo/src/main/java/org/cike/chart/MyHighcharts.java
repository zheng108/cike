package org.cike.chart;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.app.Myjackson;
import org.cike.MyBean;
import org.cike.database.DAO;
import org.cike.database.MyTableSet;
import org.cike.init.MyCache;

public class MyHighcharts {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static String getPie(String series){
		String chart="{plotOptions:{pie:{allowPointSelect: true,cursor: 'pointer'}},series:[{type: 'pie',data:%s}]}";
		//String.format(chart, getSeries(data));
		return String.format(chart, series);
	}

	private static String getSeries(MyChartData chartdata){
		String rs="";
		String type=chartdata.getType();
		List<Map> data=chartdata.getData();
		
		return rs;
	}
	
	public static String toPie(String sql) throws SQLException{ //Object... args
		
		//String sql=args[0].toString();
		//Map map=(Map)args[1];
		
		MyTableSet tableset= DAO.queryTableSet(sql);
		String data=Myjackson.toJSON(tableset.getData());
		return getPie(data);//默认样式
	}
}
