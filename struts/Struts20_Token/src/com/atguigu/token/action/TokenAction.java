package com.atguigu.token.action;

import com.opensymphony.xwork2.ActionSupport;

public class TokenAction extends ActionSupport{
	
	private static final long serialVersionUID = 1L;
	
	private String userName;
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String execute() {
		
		System.out.println("userName="+userName);
		
		return "success";
	}

}
