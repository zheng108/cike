package org.cike.ui;

public enum EnumBootstrap {
	

	DROPDOWN{
	    void init(){
	    	value=new Object[]{"dropdown","dropdown-submenu"};
		}
	};
	
	
	Object value;
	
	abstract void init();
	
}
