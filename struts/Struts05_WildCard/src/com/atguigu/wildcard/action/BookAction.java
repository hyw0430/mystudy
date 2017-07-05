package com.atguigu.wildcard.action;

public class BookAction {
	
	public String processBook(){
		System.out.println("processBook()执行了");
		return "save-success";
	}
	
	public String saveOther() {
		System.out.println("saveOther()执行了");
		return "save-success";
	}
	
	public String save() {
		System.out.println("save...");
		return "save-success";
	}
	
	public String remove() {
		System.out.println("remove...");
		return "remove-success";
	}
	
	public String edit() {
		System.out.println("edit...");
		return "edit-success";
	}
	
	public String query() {
		System.out.println("query...");
		return "query-success";
	}

}
