package com.atguigu.validation.action;

import com.opensymphony.xwork2.ActionSupport;

public class ValidationAction01 extends ActionSupport{
	
	private static final long serialVersionUID = 1L;
	
	private int age;
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public int getAge() {
		return age;
	}
	
	public String execute() {
		System.out.println("age="+age);
		return "success";
	}
	
	public String special() {
		
		System.out.println("age="+age);
		
		return SUCCESS;
	}

}
