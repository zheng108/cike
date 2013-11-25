package org.cike.init;
/**
 * Driver类型 是使用enum还是static待定
 * @author guest
 *
 */
public enum EnumDriver {
	
 H2("org.h2.Driver"),MYSQL("com.mysql.jdbc.Driver"),
 ;
 
 private String value="org.h2.Driver";//默认
 EnumDriver(String value){
  this.value=value;
 }
 
 public String value(){
 return value;
 }
 
}
