package org.cike.ui;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.MessageFormat;

public abstract class TablePort {
	public abstract void addColumn(String name);

	public abstract void addRows(Object[] rows);

	public TablePort toTableSet(ResultSet rs) {

		try {

			if (rs==null||!rs.next())
				return this; 
			ResultSetMetaData rsmd = rs.getMetaData();
			int size = rsmd.getColumnCount();
			for (int i = 0; i < size; i++) {
				addColumn(rsmd.getColumnName(i + 1));
			}

			rs.beforeFirst();

			while (rs.next()) {

				Object[] row = new Object[size];
				for (int i = 0; i < size; i++) {
					row[i] = rs.getObject(i + 1);
				}
				addRows(row);
			}
			
			rs.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this;
	}
	
	public static String limit(int page,int rows){ //小数据量？int
		int begin=(page-1)*rows;
		int end=begin+rows;
	//	return " limit "+begin+","+end;
		return MessageFormat.format(" limit {0},{1} ", begin,end);
	}
	
}
