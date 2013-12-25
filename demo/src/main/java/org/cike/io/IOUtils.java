package org.cike.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.Properties;
import java.util.logging.Logger;

import org.cike.init.MyCache;

public class IOUtils {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
       initest();
	}
	
	public static void initest() throws IOException{
		 load("db.properties");
		String rs= subStringAfter("select * from item where id>100","from");
		 info(rs);
		 
		 String  url="http://www.stats.gov.cn/tjbz/xzqhdm/t20130118_402867249.htm";
		// url="http://www.mkyong.com/";
	     rs= open(url);
		save(rs,"20130118_402867249.html");
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
			System.out.println(IOUtils.class.getClassLoader().getResource(""));
			
			InputStream in=IOUtils.class.getResourceAsStream("/"+name);
			 if(in==null){
				 info(System.getProperty("user.dir"));
				 File file=new File(System.getProperty("user.dir")+"/"+name);
				 if(file.exists()){
					 property.load(new FileInputStream(file));
				 }
				 
			 }
			property.load(in);				
	}catch(Exception e){
		e.printStackTrace();
			}
		return property;
	}
	
	
	///String
	/**
	 * 返回某字串后的字符串
	 * @param temp 原字符串
	 * @param tags 某字串
	 * @return
	 */
	public static String subStringAfter(String temp,String tags){
		int indx=temp.indexOf(tags);
        if(indx>-1){
				indx+= tags.length();
        }else{
        	return null;
        }
		return temp.substring(indx);
	}
	
	public static String subStringBefore(String temp,String tags){
		int indx=temp.indexOf(tags);
        if(indx==-1){
				
        	return null;
        }
		return temp.substring(0,indx);
	}
	
	
	public static void info(String msg){
		Logger.getLogger(IOUtils.class.getName()).info(msg);
	}
	
	public static void info(Class type,Object msg){
		//Logger.getLogger(type.getName()).info(msg); //是否缓存
		MyCache cache=MyCache.getInstance();
//		Logger log=null;
//		if(cache.has(type)){
//			 log=cache.get(type);
//		}
		Logger log=Logger.getLogger(type.getName());
		log.info(msg.toString());
	}
	
	
	
	public static void save(String text,String name) throws IOException{
		File file=new File(name);
		
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(file)));

		writer.write(text);
		writer.close();
		info(file.getAbsolutePath());
	}
	
	public static void fromURLTo(URL url,OutputStream out) throws IOException{
		//URL url = new URL(http);

		URLConnection conn = url.openConnection();
	//	String encoding = Charset.defaultCharset().name();
		String type = conn.getContentType();
		 System.out.println(conn.getHeaderField("Content-Type"));
		 type=subStringAfter(type,"charset=");
		 if(type==null)
			 type=Charset.defaultCharset().name();
		 fromTo(conn.getInputStream(),out,type);
		
 
	}
	
	private static void fromTo(InputStream in,OutputStream out,String encoding) throws IOException{
		if(in==null||out==null)return;
		BufferedInputStream bin=new BufferedInputStream(in);
		BufferedOutputStream bout=new BufferedOutputStream(out);
		byte b[]=new byte[1024*8];
		int indx=0;
		while((indx=bin.read(b))>-1){
			bout.write(b,0,indx);
		}
		
		
		
//		BufferedReader br=new BufferedReader(new InputStreamReader(in,encoding));
//		BufferedWriter bout=new BufferedWriter(new OutputStreamWriter(out,encoding));
//		String line="";
//	//	byte[] by=new byte[1024*8];
//		while((line=br.readLine())!=null){
//		   bout.write(line);
//		}
		
		in.close();
		out.close();
		//info(br.toString());
		
	}
	
	public static OutputStream getStream(String name) throws FileNotFoundException{
		///
		File file=new File(name);
		info(file.getAbsolutePath());
		
		ifDir(file);
		
		return new FileOutputStream(file);
	}
	
	private static void ifDir(File file){
		
		File pFile=file.getParentFile();
		if(pFile!=null){
		if(!pFile.exists())		
			ifDir(file.getParentFile());	   
		 pFile.mkdirs();
		}else{
			return;
		}
	}
	
	public static InputStream openStreamFromURL(String http) throws IOException{
		if(http.equals(""))return null;
		URL url = new URL(http);
		URLConnection conn = url.openConnection();
		return conn.getInputStream();
	}
	
	public static String open(String http) throws IOException{
		URL url = new URL(http);

		URLConnection conn = url.openConnection();
	//	String encoding = Charset.defaultCharset().name();
		String type = conn.getContentType();
		 System.out.println(conn.getHeaderField("Content-Type"));
		 type=subStringAfter(type,"charset=");
       // return type;
		return open(conn.getInputStream(),type);

	}
	
	private static String open(InputStream in,String encoding) throws IOException{
		StringBuffer sb=new StringBuffer();
		BufferedReader read = new BufferedReader(new InputStreamReader(in,encoding));// "GBK"

		String line = "";
		while ((line = read.readLine()) != null) {
			sb.append(line + "\n");
		}
		// System.out.println(sb);
		read.close();
		
		return sb.toString();
	}

}
