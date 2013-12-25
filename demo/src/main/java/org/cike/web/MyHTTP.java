package org.cike.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class MyHTTP {

	public static void main(String[] args) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub

		initest();
	}
	
	public static void initest() throws UnsupportedEncodingException{
		String input="http://data.stats.gov.cn/workspace/index?a=l&m=hgyd&index=A01010401%2CA01010402%2CA01010403%2CA01010404%2CA01010405%2CA01010406%2CA01010407&region=000000&time=-1%2C201201&selectId=000000&third=region";
	//http://data.stats.gov.cn/workspace/index?a=l&m=hgyd&index=A01010401,A01010402,A01010403,A01010404,A01010405,A01010406,A01010407&region=000000&time=-1,201201&selectId=000000&third=region
		System.out.print(URLDecoder.decode(input,"UTF-8"));
	}

}
