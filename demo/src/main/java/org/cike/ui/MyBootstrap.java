package org.cike.ui;

import java.lang.reflect.Array;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.app.Myjackson;
import org.cike.IPorts;
import org.cike.MyVisit;
import org.cike.data.MyFormat;
import org.cike.io.IOUtils;

/**
 * v2.3.2
 * @author guest
 *
 */
public class MyBootstrap {
	final String name="bootstrap";
	final String dropdown="dropdown";
	Map components=new HashMap();

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		initest();
	}
	
	MyBootstrap(){
		init();
	}
	
	public static void initest(){
		String data="{\"menu\": [ {\"title\": \"home\",\"href\": \"/home\"},{\"title\": \"About\",\"href\": \"/about\"},{\"title\":\"dropdown\",\"dropdown\":{\"menu\":[{\"title\": \"About\",\"href\": \"/about\"},{\"title\":\"dropdown\",\"dropdown\":{\"menu\":[{\"title\":\"dropdown\",\"dropdown\":{\"menu\":[{\"title\": \"About\",\"href\": \"/about\"}]}},{\"title\": \"About\",\"href\": \"/about\"}]}}]}}]}";
		//String data="{\"title\": \"home\",\"href\": \"/home\"}";
		MyBootstrap strap=new MyBootstrap();
	
		String rs=strap.toNavbar(data);
		
		//navtab
		 String uldata="{\"menu\": [ {\"title\": \"home\",\"href\": \"#home\"},{\"title\": \"About\",\"href\": \"#about\"},{\"title\":\"dropdown\",\"dropdown\":{\"menu\":[{\"title\": \"About\",\"href\": \"#dropabout\"}]}}]}";
		 String divdata="[{\"id\":\"home\",\"pane\":{\"p\":{\"val\":\"home\"}}},{\"id\":\"about\",\"pane\":{\"p\":{\"val\":\"about\"}}},{\"id\":\"dropabout\",\"pane\":{\"iframe\":{\"src\":\"http:www.baidu.com\",\"style\":\"width:100%;height:100%\"}}}]";
		 rs=strap.toNavTab(uldata,divdata);
		 
		 //accordion
		 data="[{\"title\":\"home\",\"href\":\"collapsehome\",\"val\":\"home\"},{\"title\":\"about\",\"href\":\"collapseabout\",\"val\":\"about\"}]";
		 rs=strap.toAccordion(data);
		 IOUtils.info(MyBootstrap.class,rs);
		 
	}
	
	private void init(){
		Properties p=IOUtils.load(name+".properties");
		MyVisit.visit(p, new IPorts(){

			public Object execute(Object... obj) {
				// TODO Auto-generated method stub
				String key=obj[0].toString();
				Object val=Myjackson.fromJSON(obj[1].toString());
				components.put(key, val);
				return null;
			}
			
		});
		
	}
	
	/**
	 * 简单 导航 
	 * 参见http://v2.bootcss.com/examples/starter-template.html
	 * div:navbar + ul:nav
	 * @param jsondata
	 * @return
	 */
	public String toNavbar(String jsondata){
		
		Map map=(Map)Myjackson.fromJSON(jsondata);
		
//		final StringBuffer sb=new StringBuffer();
		
//		sb.append("<div class='navbar navbar-inverse navbar-fixed-top'><div class='navbar-inner'><div class='container'>");
//		
//		sb.append("<div class='nav-collapse collapse'><ul class='nav'>");
//	
//		sb.append(getNav(map));
//		
//		sb.append("</ul></div>");
//		sb.append("</div></div></div>");
		
		//简单的鼠标 hover事件
		
//		IOUtils.info(MyBootstrap.class,sb);
//		return sb.toString();
		
		//配置化
		String rs="";
		List<String> classlist=(List)((Map)components.get("navbar")).get("div"); //不处理异常?
//		List<String> navlist=(List)((Map)components.get("nav")).get("ul"); //不处理异常?
//		
//		String ul=getUL(navlist.get(0));//默认
//	    ul=String.format(ul, getNav(map));
		String ul=toNav(map,"navbar",NAV.DEFAULT.ordinal());
		rs=String.format(getDIV(classlist), ul);
		
		return rs;
	}
	
private String toNav(Map map,String ctype,int navtype){
		//配置化
	//	String rs="";
	//	List<String> classlist=(List)((Map)components.get(ctype)).get("div"); //不处理异常?
		List<String> navlist=(List)((Map)components.get("nav")).get("ul"); //不处理异常?
		
		String ul=getUL(navlist.get(navtype));
	    ul=String.format(ul, getNav(map));
	//	rs=String.format(getDIV(classlist), ul);
		IOUtils.info(MyBootstrap.class,ul);
		return ul;
	}
	

    /**
     * 
     * uldata转换层级结构 为UL  格式为 menu[｛title href/dropdown{menu} ｝,]
     * divdata转换层级结构为DIV 格式为 ［{id pane｛p{val}/iframe{src style}｝},］
     * @param uldata
     * @param divdata
     * @return
     */
	public String toNavTab(String uldata,String divdata){
	
		Map ulmap=(Map)Myjackson.fromJSON(uldata);
		List divlist=Myjackson.fromJSON(divdata,ArrayList.class);
	
		String ul=toNav(ulmap,"navtab",NAV.NAVTABS.ordinal());
		
		
	
		List<String> classlist=(List)((Map)components.get("navtab")).get("div"); //不处理异常?
		
		String div=getPaneDIV(classlist,divlist);
		
		return ul+div;
	}
	
	public String toAccordion(String jsondata){
		List list=Myjackson.fromJSON(jsondata, ArrayList.class);
		List<Object> classlist=(List)((Map)components.get("accordion")).get("div"); //不处理异常?
        String div=getCollapseDIV(classlist,list,"a1"); //有异
		return div;
	}
	
	private String getNavli(Map map){
		final StringBuffer sb=new StringBuffer();
		
		//只有一级的简单nav
//		MyVisit.visit(map, new IPorts(){
//			public Object execute(Object... obj) {
//				// TODO Auto-generated method stub
//				if(obj[0].equals("menu")){
//					List<Map> list=(ArrayList)obj[1];
//					for(Map map : list){
//						Object title=map.get("title");
//						Object href=map.get("href");
//						sb.append(String.format("<li><a href='%s'>%s</a></li>",href, title));
//					}			
//				}
//          		return null;
//			}		
//		});
		
		
		
		
		return sb.toString();
	}
	
	public String getNav(Map map){
	     return getNav(map,dropdown);
	}
	
	/**
	 * 下拉菜单 导航
	 * 更通用的接口 map->json / 或不限定属性名而仅提供层级结构 待定
	 * 转换层级结构 为UL 格式为 menu[｛title href/dropdown{menu} ｝,]
	 * 第一级li class为dropdown
	 * 第>1级li class为dropdown-submenu
	 * @param map
	 * @param li
	 * @return
	 */
	private String getNav(Map map,final String li){
		final StringBuffer sb=new StringBuffer();
		
		Map navdown=(Map)components.get(dropdown);
		final String ul,subli,a;
		ul=navdown.get("ul").toString();
		subli=((List)navdown.get("li")).get(1).toString();
		a=navdown.get("a").toString();
		
		MyVisit.visit(map, new IPorts(){
			public Object execute(Object... obj) {
				// TODO Auto-generated method stub
				if(obj[0].equals("menu")){
					List<Map> list=(ArrayList)obj[1];
					for(Map map : list){
						if(map.containsKey("href")){
						Object title=map.get("title");
						Object href=map.get("href");
						sb.append(String.format("<li><a href='%s'>%s</a></li>",href, title));
						}else if(map.containsKey("dropdown")){
							Object title=map.get("title");
							sb.append(String.format("<li class='%s'>",li));
							//sb.append(String.format(" <a href='#' class='dropdown-toggle' >%s</a>", title));// data-toggle='dropdown' <b class='caret'></b>
						   //sb.append("<ul class='dropdown-menu'>");
						   // sb.append(getNav((Map)map.get("dropdown"),"dropdown-submenu"));
							
							sb.append(String.format(" <a href='#' class='%s' >%s<b class='caret'></b></a>", a,title));
						    sb.append(String.format("<ul class='%s'>",ul));
						    sb.append(getNav((Map)map.get("dropdown"),subli));
						    sb.append("</ul></li>");
						}
					}			
				}
          		return null;
			}		
		});
		
		return sb.toString();
	}
	
	
	
	
	///////////
	/**
	 * 纯div仅class
	 * @param classlist
	 * @return
	 */
	private String getDIV(List<String> classlist){
		StringBuffer sb=new StringBuffer();
		for(String div : classlist){
			sb.append(String.format("<div class='%s'>", div));
		}
		sb.append("%s");  //format
		int size=classlist.size();
		for(int i=0;i<size;i++){
			sb.append("</div>");
		}
		return sb.toString();
		
	}
	
	
	/**
	 * 最后一级DIV的节点 由datalist定义
	 * @param classlist
	 * @param datalist
	 * @return
	 */
	private String getPaneDIV(List<String> classlist,List<Map> datalist){
		final StringBuffer sb=new StringBuffer();
		int size=classlist.size();
		for(int i=0;i<size-1;i++ ){
			sb.append(String.format("<div class='%s'>", classlist.get(i)));
		}
		
		String dataclass=classlist.get(size-1).toString();
		
		size=datalist.size();
		for(int i=0;i<size;i++){
			Map map=datalist.get(i);
			sb.append(String.format("<div id='%s' class='%s'>", map.get("id"),dataclass));
			sb.append(getPane((Map)map.get("pane")));
			sb.append("</div>");
		}
		
		size=classlist.size();
		for(int i=0;i<size-1;i++){
			sb.append("</div>");
		}
		return sb.toString();
		
	}
	
	private String getPane(Map map){
		final StringBuffer sb=new StringBuffer();
		MyVisit.visit(map, new IPorts(){

			public Object execute(Object... obj) {
				// TODO Auto-generated method stub
				String rs="<{0} {1}>{2}</{0}>";
				String key=obj[0].toString();
				Object val="";
				Map map=(Map)obj[1];
				if(map.containsKey("val")){
					val=map.remove("val");
				}
				String attr=MyHTML.toAttribute(map);
				sb.append(MessageFormat.format(rs, key,attr,val));
				return null;
			}
			
		});
	   return sb.toString();
	}
	
	/**
	 * 倒数第二级DIV有 多个
	 * 最后一
	 * @param classlist
	 * @param datalist
	 * @return
	 */
	private String getCollapseDIV(List<Object> classlist,List<Map> datalist,String id){
		
		StringBuffer sb=new StringBuffer();
		int size=classlist.size();
		sb.append(String.format("<div class='%s' id='%s'>",classlist.get(0),id));
		for(int i=1;i<size-2;i++){
			sb.append(String.format("<div class='%s'>", classlist.get(i)));
		}
		
		for(Map map : datalist){
			String collapse=getCollapse(map,(List)classlist.get(size-1),id);
			sb.append(String.format("<div class='%s'>%s</div>", classlist.get(size-2),collapse));
		}
		
		
		sb.append("</div>");
		return sb.toString();
	}
	
	private String getCollapse(Map map,List<Map> list,String id){
		
		final StringBuffer sb=new StringBuffer();
		final StringBuffer format=new StringBuffer();
		
		for(Map m : list){
			MyVisit.visit(m, new IPorts(){

				public Object execute(Object... obj) {
					// TODO Auto-generated method stub
					if(obj[1] instanceof List){
						List list=(List)obj[1];
						int size=list.size();
						int indx=size;
						for(int i=0;i<size;i++){
							if(list.get(i) instanceof Map){
								format.append(MyHTML.byClass((Map)list.get(i)));
							      indx--;
							}else
							format.append(MessageFormat.format("<{0} class=\"{1}\" %s>",obj[0],list.get(i)));
						}
						
						for(int i=0;i<indx;i++){
							format.append(MessageFormat.format("%s</{0}>",obj[0]));
						}
						
					}
//					else{
//						format.append(MessageFormat.format("<{0} class='{1}' %s>%s</{0}>", obj[0],obj[1]));
//						
//					}
					return null;
				}
				
			});
		}
		
		String href=String.format(" href='#%s' ", map.get("href"));
		String title=map.get("title").toString();
		
		String attr=String.format(" id='%s' data-parent='#%s'",map.get("href"),id);
		String val=map.get("val").toString();
		sb.append(String.format(format.toString(), "",href,title,"",attr,"",val,""));
		
		
		
		return sb.toString();
	}
	private String getUL(String ulclass){
		StringBuffer sb=new StringBuffer();
		sb.append(String.format("<ul class='%s'>",ulclass));
		sb.append("%s</ul>"); //format
		return sb.toString();
	}

//	private String getHTML(String tag,String tagclass){
//		return "";
//	}
}

enum NAV{
	DEFAULT,NAVLIST,NAVTABS
}




