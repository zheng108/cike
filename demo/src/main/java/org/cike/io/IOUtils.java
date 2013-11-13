package org.cike.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Logger;

public class IOUtils {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        load("db.properies");
	}
	
	
	/**
	 * web项目 获取项目路径较麻烦 property.load(new FileInputStream(file));
	 * 故将propertie文件放于某路径下直接读取（与class同目录）
	 * @param name
	 * @return
	 */
	public static Properties load(String name){
		Properties property=new Properties();
		try {
			InputStream in=Object.class.getResourceAsStream("/"+name);
			 if(in==null){
				 info(System.getProperty("user.dir"));
				 File file=new File(System.getProperty("user.dir")+"/"+name);
				 if(file.exists()){
					 property.load(new FileInputStream(file));
				 }
				 
			 }
			property.load(in);				
	}catch(Exception e){
			}
		return property;
	}
	
	public static void info(String msg){
		Logger.getLogger(IOUtils.class.getName()).info(msg);
	}

}
