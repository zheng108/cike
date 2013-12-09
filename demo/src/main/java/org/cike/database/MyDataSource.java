package org.cike.database;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.cike.init.EnumDriver;
import org.cike.init.MyDefault;
import org.cike.io.IOUtils;

public class MyDataSource implements DataSource{ //ConnectionPoolDataSource 
	private String properties="db.properties";
	private EnumDriver driver=EnumDriver.H2; //默认
	ThreadLocal<Connection> connection=new ThreadLocal<Connection>();
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		initest();
	}
	
	public static void initest()
	{
		MyDataSource ds=new MyDataSource("H2");
		Connection con=null;
		
	    try {
			 con=ds.getConnection();
			IOUtils.info(con.toString());
			IOUtils.info(""+con.isClosed());
				
			
			con=ds.getConnection();
			 IOUtils.info(con.toString());
			 IOUtils.info(""+con.isClosed());
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ds.close();
		}
	    
	    try {
			IOUtils.info(con.toString()+""+con.isClosed());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
	}
	
	public MyDataSource(){
		//driver=EnumDriver.H2;//默认
		driver=MyDefault.getDriver();//IOC?
	}
	
	
	public MyDataSource(String type){
		driver=EnumDriver.valueOf(type.toUpperCase());
	}
	

	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public void setLogWriter(PrintWriter out) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void setLoginTimeout(int seconds) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	public Connection getConnection() throws SQLException {
		// TODO Auto-generated method stub
		Connection con=connection.get();
		if(con==null){
			con=getConnection(driver);
			connection.set(con);
		}
		return con;
	}

	public Connection getConnection(String username, String password)
			throws SQLException {
		// TODO Auto-generated method stub
		return getConnection();
	}
	
	
	private Connection getConnection(EnumDriver driver){
		Properties property=IOUtils.load(properties);
		Connection con=null;
		try {
			Class.forName(driver.value());
			
			String username=property.getProperty("username").trim();
			String password=property.getProperty("password").trim();

			String url=String.format("jdbc:%s:%s",driver.toString().toLowerCase(),property.getProperty("url"));
		    con=DriverManager.getConnection(url,username,password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
	
	}
	
	public  void close(){
		Connection con=connection.get();
		if(con!=null){
			try {
				con.close();
				connection.remove();  //从线程局部变量中移除con，如果没有移除掉，下次还会用这个已经关闭的连接，就会出错  
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
