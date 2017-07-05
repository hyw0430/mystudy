package com.atguigu.validation.action;

import com.opensymphony.xwork2.ActionSupport;

public class ValidationAction06 extends ActionSupport{

	private static final long serialVersionUID = 1L;
	
	private String idcard;
	
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	
	public String getIdcard() {
		return idcard;
	}
	
	public String execute() throws Exception {
		
		System.out.println("idCard="+idcard);
		
		return SUCCESS;
	}

}
