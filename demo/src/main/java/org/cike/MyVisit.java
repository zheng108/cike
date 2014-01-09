package org.cike;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


public class MyVisit {
//	public static void visit(ResultSet rs,Ports port){
//		try {
//			//rs.beforeFirst();
//			
//			while(rs.next()){
//				port.execute(rs);
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	public static void visit(Map map,IPorts port){
		Set<Entry> set=map.entrySet();
		for(Entry entry:set){
			port.execute(entry.getKey(),entry.getValue());
		}
		
	}
	
	
	
//	public static void visit(Enumeration enumeration,Ports port){
//		while(enumeration.hasMoreElements()){
//			port.execute(enumeration.nextElement());
//		}
//	}
	
	public static void visit(Class clazz,Ports port){
		BeanInfo info = null;
		try {
			info = Introspector.getBeanInfo(clazz);
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PropertyDescriptor[] pds=info.getPropertyDescriptors();
		
		for(PropertyDescriptor pd:pds){
			port.execute(pd);
		}
	
	}
	
	public static void visit(File file,Ports port){
	
		if(file.isDirectory()){
		for(File f : file.listFiles()){
			
				visit(f,port);
			
		}
		}else{
			port.execute(file);
		}
	}
	
	
}
