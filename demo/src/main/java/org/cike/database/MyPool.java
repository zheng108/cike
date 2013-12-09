package org.cike.database;

import org.h2.jdbcx.JdbcConnectionPool;

public class MyPool {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		initest();
	}
	
	public static void initest(){
		String url="";
		String username="";
		String password="";
		JdbcConnectionPool cp=JdbcConnectionPool.create(url, username, password);
	}

}
