package org.cike.init;

import java.util.Properties;

import javax.sql.DataSource;

import org.cike.IPorts;
import org.cike.MyCache;
import org.cike.MyVisit;
import org.cike.database.DAO;
import org.cike.io.IOUtils;

/**
 * 配置枚举工厂？
 * @author guest
 *
 */
public class MyDefault {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		 initest();
	}
	
	public static void initest(){
		//MyDefault mdf=new MyDefault();
		 String rs=   getDriver().value();
		 
		 IOUtils.info(rs);
		 IOUtils.info(getUI().getSimpleName());
		 IOUtils.info(getDataSource().toString());
		 
	}
	
	
	/**
	 * 
	 * 配置默认default.properties ,value为枚举值
	 * 枚举类型位于包  org.cike.init下,命名 约定 为 Enum+key
	 * 
	 */
	static{
		Properties property=IOUtils.load("default.properties");
		if(property!=null){
			MyVisit.visit(property, new IPorts(){

				public Object execute(Object... obj) {
					// TODO Auto-generated method stub
					String key=obj[0].toString();
					String val=obj[1].toString().toUpperCase();
					Class type=null;
					try {
						type=Class.forName("org.cike.init.Enum"+key);
						MyCache.put(key,Enum.valueOf(type, val));
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				
					
					return null;
				}
				
			});
		}
		
	}
	
	public static EnumDriver getDriver(){
		
		EnumDriver driver=(EnumDriver) MyCache.get("Driver");
		if(driver==null){
			driver=EnumDriver.H2;
		}
		return driver;
	}
	
	public static Class getUI(){
		return ((EnumUI)MyCache.get("UI")).getUI();
	}
	
	public static DataSource getDataSource(){
		EnumDatasource ds=(EnumDatasource)MyCache.get("Datasource");
		if(ds==null){
			ds=EnumDatasource.DEFAULT;
		}
		return ds.getDatasource();
	}
	
	public static DAO getDAO(){
		DAO dao=(DAO)MyCache.get("DAO");
		if(dao==null){
			dao=EnumDAO.DEFAULT.getDAO();
		}
		
		return dao;
	}

}
