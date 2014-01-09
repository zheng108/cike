package org.cike.ui;

import java.text.MessageFormat;
import java.util.Map;

import org.app.Myjackson;
import org.cike.IPorts;
import org.cike.MyVisit;
import org.cike.io.IOUtils;

public class MyHTML {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		initest();
	}
	
	public static void initest(){
		String data="{\"menu\": [ {\"title\": \"home\",\"href\": \"/home\"},{\"title\": \"About\",\"href\": \"/about\"},{\"title\":\"dropdown\",\"dropdown\":{\"menu\":[{\"title\": \"About\",\"href\": \"/about\"},{\"title\":\"dropdown\",\"dropdown\":{\"menu\":[{\"title\":\"dropdown\",\"dropdown\":{\"menu\":[{\"title\": \"About\",\"href\": \"/about\"}]}},{\"title\": \"About\",\"href\": \"/about\"}]}}]}}]}";
		//String data="{\"title\": \"home\",\"href\": \"/home\"}";
		MyBootstrap strap=new MyBootstrap();
	//	String rs=strap.toNavbar(data);
		data="{\"src\":\"http:www.baidu.com\",\"style\":\"width:100%;height:100%\"}";
		String rs=toAttribute((Map)Myjackson.fromJSON(data));
		data="{\"a\":\"accordion\"}";
		rs=byClass((Map)Myjackson.fromJSON(data));
		IOUtils.info(rs);
	}
	
	public static String toAttribute(Map map){
		
		return format(map," {0}=\"{1}\" ");
		
	}
	
	/**
	 * 仅并列 class
	 * @param map
	 * @return
	 */
	public static String byClass(Map map){
	
		return format(map,"<{0} class=\"{1}\" %s>%s</{0}>");
	}
	
	static String format(Map map,final String format){
		final StringBuffer sb=new StringBuffer();
		MyVisit.visit(map, new IPorts(){

			public Object execute(Object... obj) {
				// TODO Auto-generated method stub
				sb.append(MessageFormat.format(format,obj[0],obj[1]));
				return null;
			}
			
		});
		
		return sb.toString();
	}

}
