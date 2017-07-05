package com.atguigu.webres.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;

public class InjectOldResAction implements ServletRequestAware,ServletContextAware{

	private ServletContext application;
	private HttpServletRequest request;

	@Override
	public void setServletContext(ServletContext context) {
		this.application = context;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public String execute() {
		
		System.out.println(request);
		
		System.out.println(request.getSession());
		
		System.out.println(application);
		
		return "success";
	}

}
