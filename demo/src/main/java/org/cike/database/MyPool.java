package org.cike.database;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class MyPool {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public DataSource getDataSource(){
		return new ComboPooledDataSource();
	}

}
