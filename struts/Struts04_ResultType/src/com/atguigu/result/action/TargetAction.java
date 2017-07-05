package com.atguigu.result.action;

import com.opensymphony.xwork2.ActionSupport;

public class TargetAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	
	@Override
	public String execute() throws Exception {
		
		System.out.println("目标Action执行了！");
		
		return SUCCESS;
	}

}
