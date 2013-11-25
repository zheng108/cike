package org.cike.ui;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cike.MyBean;
import org.cike.database.DAO;
import org.cike.database.H2SQL;
import org.cike.database.MyJDBC;
import org.cike.init.MyDefault;
import org.cike.io.IOUtils;
import org.cike.io.MyFormat;

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
		
		eUI.toTable(H2SQL.ALLITEM, 1, 10);
		
		return eUI;
	}

	static class Gridata {
		public long total = 0;
		public List<Map> columns = new ArrayList<Map>();
		public List<Map> rows = new ArrayList();
	}

	/**
	 * 
	 * @param query
	 * @param page
	 * @param rows
	 * @return
	 */
	public String toTable(String query, int page, int rows) {
		String rs = "";
		DAO dao=MyDefault.getDAO();
		Object obj=MyBean.execute(dao, "query", query);

		return rs;
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
		return MyFormat.toJSON(gridata);
	}

}
