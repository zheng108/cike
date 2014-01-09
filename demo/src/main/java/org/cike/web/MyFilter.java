package org.cike.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.cike.Ports;
import org.cike.chart.MyHighcharts;
import org.cike.chart.MyNVD3;
import org.cike.domain.MyChina;
import org.cike.domain.MyInvoice;
import org.cike.init.MyCache;
import org.cike.ui.MyEasyUI;

public class MyFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		chain.doFilter(request, response);
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

		MyCache.init(new Ports(){

			public Object execute(Object obj) {
				// TODO Auto-generated method stub
				Map map=new HashMap();
				map.put("UI", MyEasyUI.class);
				
				map.put("CHART", MyNVD3.class);//MyHighcharts.class
				
				//domain
				map.put("CHINA", MyChina.class);
				map.put("INVOICE", MyInvoice.class);
				
				return map;
			}
			
		});
		
	}

}
