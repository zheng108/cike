package org.cike.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.app.Myjackson;
import org.cike.MyBean;

public class MyChildrenNode {

	Object node;
	List<MyChildrenNode> children;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	

	public MyChildrenNode(Object node){
		this.node=node;
	}
	
	public void addChild(Object node){
		if(children==null){
			children=new ArrayList();
		}
		children.add(new MyChildrenNode(node));
	}
	
	public void addChild(Map nodes){
		if(children==null){
			children=new ArrayList();
		}
		if(nodes==null)return;
		for(Object obj:nodes.values()){
		children.add(new MyChildrenNode(obj));
		}
	}
	
	public Object getParam(String val){
		return MyBean.getField(node, val);
	}


	public Object getNode() {
		return node;
	}


	public void setNode(Object node) {
		this.node = node;
	}


	public List<MyChildrenNode> getChildren() {
		return children;
	}


	public void setChildren(List<MyChildrenNode> children) {
		this.children = children;
	}
	
	@Override
	public String toString(){
		//return MessageFormat.format("'{ {0} '}");
		String rs=Myjackson.toJSON(node);
		return rs;
	}
	
	

}
