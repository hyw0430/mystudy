package com.atguigu.webres.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

public class OldWebResAction {
	
	public String execute() {
		
		//通过ServletActionContext获取原生的Web资源对象
		HttpServletRequest request = ServletActionContext.getRequest();
		System.out.println(request);
		
		HttpSession session = request.getSession();
		System.out.println(session);
		
		ServletContext servletContext = ServletActionContext.getServletContext();
		System.out.println(servletContext);
		
		return "success";
	}

}
