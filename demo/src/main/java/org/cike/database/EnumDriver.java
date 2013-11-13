package org.cike.database;
/**
 * Driver类型 是使用enum还是static待定
 * @author guest
 *
 */
public enum EnumDriver {
	
 H2("org.h2.Driver"),MYSQL("com.mysql.jdbc.Driver"),
 ;
 
 private String value;
 EnumDriver(String value){
  this.value=value;
 }
 
 public String value(){
 return value;
 }
 
}
