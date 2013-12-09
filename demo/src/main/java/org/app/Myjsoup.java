package org.app;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.cike.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.examples.HtmlToPlainText;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Myjsoup {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		initest();
	}
	
	public static void initest() throws IOException{
		Document doc = Jsoup.connect("http://en.wikipedia.org/").get();
//		Elements elements= doc.select("#mp-itn b a");
//		for(Element e : elements){
//			System.out.println(e.attr("abs:href")+e.text());
//		}
		
		//将html转化为纯文本
		HtmlToPlainText formatter = new HtmlToPlainText();
        String plainText = formatter.getPlainText(doc);
      //  System.out.println(plainText);
        
        
        Myjsoup jsoup=new Myjsoup();
        String url="http://www.stats.gov.cn/tjbz/xzqhdm";
        String select="a[href]";
        
        
        url="http://www.stats.gov.cn/tjbz/";
        select="td > a.a2[href]";
        jsoup.getHtml(url,select); //国家统计局
	
        //
	}
	
	public void getHtml(String url,String select) throws IOException{
	//	Document doc = Jsoup.connect(url).get();
		Document doc=Jsoup.parse(IOUtils.open(url));
		Elements elements= doc.select(select);
		
//		HtmlToPlainText formatter = new HtmlToPlainText();
//       String text = formatter.getPlainText(doc);
//       
//       IOUtils.save(text, "20130118_402867249");
//     

		for(Element e : elements){
			String href=e.attr("href");
			String html=e.attr("abs:href");
			if(!href.equals(html)){
			 href=url+href;
			}
			IOUtils.fromURLTo(new URL(href), IOUtils.getStream("data/"+e.text()));
			//FileUtils.copyURLToFile(new URL(href), new File(e.text()));
		}
   
	}
	
	/**
	 * 获取统计用区划代码和城乡划分代码
	 * @throws IOException
	 */
	public void getDMcsv() throws IOException {
		String url = "http://www.stats.gov.cn/tjbz/xzqhdm/t20130118_402867249.htm";
		String select = "p.MsoNormal";

		// Document doc = Jsoup.connect(url).get(); bug
		Document doc = Jsoup.parse(IOUtils.open(url));
		Elements elements = doc.select(select);

		// HtmlToPlainText formatter = new HtmlToPlainText();
		// String text = formatter.getPlainText(doc);
		//
		// IOUtils.save(text, "20130118_402867249");
		//

		StringBuffer sb = new StringBuffer();
		boolean odd = false; // 奇偶行
		for (int i = 0; i < elements.size(); i++) {
			Element e = elements.get(i);
			if (e.text().equals(""))
				continue;
			if (odd) {
				sb.append(",").append(e.text()).append("\n");
			} else {
				sb.append(e.text());
			}
			odd = !odd;
		}

		IOUtils.save(sb.toString(), "20130118_402867249.csv");
	}


}
