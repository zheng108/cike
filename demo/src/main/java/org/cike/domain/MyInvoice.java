package org.cike.domain;

import java.util.HashMap;

import org.cike.database.MyJDBC;
import org.cike.init.MyCache;

/**
 * 模拟业务 数据库使用HSQLDB示例数据
 * @author guest
 *
 */
public class MyInvoice {
	static String InvoiceInfo="SELECT invoiCE.ID ,firstnamE ,street ,total from inVOICE join cUSTOMER  on cUSTOMERID =cuSTOMER.id";
    static String AreaInfo="SELECT city,count(*) FROM CUSTOMER group by city";
	final static String DOMAIN="INVOICE";
	static{
		//MyCache.put(DOMAIN, MyInvoice.class);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		initest();
	}
	
	public static void initest(){
		MyJDBC.query(InvoiceInfo);
	}
	
	/**
	 * 发票详情 
	 * table形式
	 * @param param
	 * @return
	 */
	public static Object getInvoice(HashMap param){
		Object rs=null;
		if(param.containsKey("domain")&&DOMAIN.equals(param.get("domain").toString().toUpperCase())){
		       if(param.containsKey("module")&&param.get("module").equals("Table"))
		       rs=new Object[]{InvoiceInfo,param};  //toTable 参数 
		}
			return rs;
	
	}
	
	
	/**
	 * 分布区域统计
	 * Pie
	 * @param param
	 * @return
	 */
	public static Object getCity(HashMap param){
		Object rs=null;
		if(param.containsKey("domain")&&DOMAIN.equals(param.get("domain").toString().toUpperCase())){
		       if(param.containsKey("module")&&param.get("module").equals("Pie"))
		 //      rs=new Object[]{AreaInfo,param};  //toPie 参数 ,可一个参数？
		    	   rs=AreaInfo;
		}
			return rs;
	
	}

}
