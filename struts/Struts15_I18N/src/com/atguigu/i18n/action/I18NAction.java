package com.atguigu.i18n.action;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

public class I18NAction extends ActionSupport{
	
	private static final long serialVersionUID = 1L;
	
	public String getValueStackMessage() {
		return "Message From ValueStack";
	}
	
	public String testValueStackMsg() {
		return "success";
	}

	public String execute() {
		
		String userName = getText("userName");
		System.out.println("userName="+userName);
		
		//带占位符的字符串，可以将占位符参数封装到List集合中
		List<String> paramList = Arrays.asList("Good day");
		
		//给占位符传参数
		String today = getText("today",paramList);
		System.out.println("today="+today);
		
		//Struts2会自动根据当前Locale对象将日期对象进行格式化
		List<Date> dataParam = Arrays.asList(new Date());
		
		today = getText("today",dataParam);
		System.out.println("today="+today);
		
		return "success";
	}

}
