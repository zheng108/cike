package org.cike.init;

import javax.sql.DataSource;

import org.cike.database.DAO;
import org.cike.database.MyDataSource;
import org.cike.database.MyJDBC;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public enum DefaultEnum {

	// /EnumDriver

}

enum EnumDatasource {
	DEFAULT {
		@Override
		DataSource getDatasource() {
			// TODO Auto-generated method stub
			return new MyDataSource();
		}
	},
	C3P0 {
		@Override
		DataSource getDatasource() {
			// TODO Auto-generated method stub
			return new ComboPooledDataSource();
		}
	},
	DBCP

	{
		@Override
		DataSource getDatasource() {
			// TODO Auto-generated method stub
			return null;
		}
	};
	abstract DataSource getDatasource();
}

enum EnumDAO {
	DEFAULT() {
		@Override
		DAO getDAO() {
			// TODO Auto-generated method stub
			return new MyJDBC();
		}
	}, DBUtils() {
		@Override
		DAO getDAO() {
			// TODO Auto-generated method stub
			return null;
		}
	};

	
	abstract DAO getDAO();
}
