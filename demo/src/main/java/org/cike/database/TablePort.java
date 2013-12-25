package org.cike.database;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.MessageFormat;

public abstract class TablePort {
	public abstract void addColumn(String name);

	public abstract void addRows(Object[] rows);

	public TablePort toTableSet(ResultSet rs) {

		try {

			if (rs==null)
				return this; 
			else{
				rs.beforeFirst();
			}
			
			ResultSetMetaData rsmd = rs.getMetaData();
			int size = rsmd.getColumnCount();
			for (int i = 0; i < size; i++) {
				addColumn(rsmd.getColumnName(i + 1));
			}

			

			while (rs.next()) {

				Object[] row = new Object[size];
				for (int i = 0; i < size; i++) {
					row[i] = rs.getObject(i + 1); //待优化
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
	

}
