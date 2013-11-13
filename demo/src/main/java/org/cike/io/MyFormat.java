package org.cike.io;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.fasterxml.jackson.xml.XmlMapper;

public class MyFormat {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		initest();
	}
	
	public static void initest(){
		List list=new ArrayList();
		list.add("cike");
		list.add(1101);
		Map map=new HashMap();
		map.put("name", "cike");
		map.put("id", 1101);
		
		
		IOUtils.info(toXML(list));
		IOUtils.info(toJSON(map));
		
	}
	
	public static String toXML(Object value){
		String rs="";
		StringWriter w=new StringWriter();
		XmlMapper xml=new XmlMapper();
		try {
			xml.writeValue(w, value);
			rs=w.toString();
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public static String toJSON(Object value){
	
		String rs="";
		StringWriter w=new StringWriter();
		ObjectMapper mapper=new ObjectMapper();
		try {
			mapper.writeValue(w, value);
			rs=w.toString();
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

}