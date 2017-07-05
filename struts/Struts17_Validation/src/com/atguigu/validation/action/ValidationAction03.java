package com.atguigu.validation.action;

import com.opensymphony.xwork2.ActionSupport;

public class ValidationAction03 extends ActionSupport{

	private static final long serialVersionUID = 1L;
	
	private String email;
	
	public String execute() {
		System.out.println("email="+email);
		return SUCCESS;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}

}
