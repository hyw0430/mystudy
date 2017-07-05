package com.atguigu.extension.action;

import com.opensymphony.xwork2.ActionSupport;

public class ExtensionAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	
	public String otherMethod() {
		System.out.println("通过动态方法调用执行了：otherMethod()");
		return SUCCESS;
	}
	
	@Override
	public String execute() throws Exception {
		
		System.out.println("ExtensionAction执行了，证明当前请求是Struts2请求");
		
		return SUCCESS;
	}

}
