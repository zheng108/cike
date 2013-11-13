package org.cike.impl;

import java.util.Map;

import org.cike.IPorts;
import org.cike.MyVisit;
import org.cike.Ports;
import org.cike.io.IOUtils;

public class MyImpl {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static class ShowMap implements IPorts{

		public Object execute(Object... obj) {
			// TODO Auto-generated method stub
			IOUtils.info(obj[0]+":"+obj[1]);
			return null;
		}
		
	}
	
	public static class ShowMapList implements Ports{

		public Object execute(Object obj) {
			// TODO Auto-generated method stub
			Map map=(Map)obj;
			MyVisit.visit(map, new ShowMap());
			return null;
		}
		
	}

}
