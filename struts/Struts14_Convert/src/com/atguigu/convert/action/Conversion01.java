package com.atguigu.convert.action;

import com.opensymphony.xwork2.ActionSupport;

public class Conversion01 extends ActionSupport{
	
	private static final long serialVersionUID = 1L;
	
	private int age;
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public String execute() {
		System.out.println("age="+age);
		return "success";
	}

}
