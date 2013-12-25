package org.cike.init;

import javax.sql.DataSource;

import org.cike.database.DAO;
import org.cike.database.MyDataSource;
import org.cike.database.MyJDBC;
import org.cike.ui.MyEasyUI;

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

 enum EnumUI {
	EASYUI {
		@Override
		public Class getUI() {
			// TODO Auto-generated method stub
			return MyEasyUI.class;
		}
	},
	BOOTSTRAP

	{
		@Override
		public Class getUI() {
			// TODO Auto-generated method stub
			return null;
		}
	};
	public abstract Class getUI();
}

//怪怪的
//enum EnumDAO {
//	DEFAULT() {
//		@Override
//		DAO getDAO() {
//			// TODO Auto-generated method stub
//			return new MyJDBC();
//		}
//	}, DBUtils() {
//		@Override
//		DAO getDAO() {
//			// TODO Auto-generated method stub
//			return null;
//		}
//	};
//
//	
//	abstract DAO getDAO();
//}
