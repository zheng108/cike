package org.cike.impl;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.cike.io.IOUtils;

public class MyShow {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static void listMap(List<Map<String, Object>> listmap){
		long bl=System.nanoTime();
		for(Map map: listmap){
			Set<Entry> set=map.entrySet();
			for(Entry entry:set){
				System.out.print(String.format(" %s:%s ", entry.getKey(),entry.getValue()));
			}
			System.out.println("");
		}
		
		long el=System.nanoTime();
		IOUtils.info((el-bl)+"mm");
	}

}
