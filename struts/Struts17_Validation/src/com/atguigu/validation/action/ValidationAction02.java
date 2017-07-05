package com.atguigu.validation.action;

import com.opensymphony.xwork2.ActionSupport;

public class ValidationAction02 extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private String userPwd;
	private String userPwdAgain;
	
	public String execute() throws Exception {
		
		System.out.println("userPwd="+userPwd);
		System.out.println("userPwdAgain="+userPwdAgain);
		
		return SUCCESS;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserPwdAgain() {
		return userPwdAgain;
	}

	public void setUserPwdAgain(String userPwdAgain) {
		this.userPwdAgain = userPwdAgain;
	}

}
