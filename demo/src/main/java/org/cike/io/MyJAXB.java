package org.cike.io;

/**
 * 有问题？
 */
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class MyJAXB {

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
		String rs=toString(list);
		
		IOUtils.info(rs);
		
	}
	
	public static String toString(Object obj){
		String rs="";
		try {
			JAXBContext context=JAXBContext.newInstance(obj.getClass());
		  //  Unmarshaller marshal=context.createUnmarshaller();
			Marshaller marshal=context.createMarshaller();
			   
		    StringWriter out=new StringWriter();
		   // marshal.marshal(obj,out);
		    JAXB.marshal(obj,out);
		    rs=out.toString();
		    
		
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

}
