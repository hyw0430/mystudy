package com.atguigu.webres.action;

import java.util.Map;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

public class InjectResAction implements RequestAware,ParameterAware,SessionAware,ApplicationAware{

	private Map<String, Object> appMap;
	private Map<String, Object> sessMap;
	private Map<String, String[]> paramMap;
	private Map<String, Object> reqMap;

	public String execute() {
		
		System.out.println("appMap="+this.appMap);
		System.out.println("sessMap="+this.sessMap);
		System.out.println("paramMap="+this.paramMap);
		System.out.println("reqMap="+this.reqMap);
		
		return "success";
	}
	
	@Override
	public void setApplication(Map<String, Object> application) {
		this.appMap = application;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.sessMap = session;
	}

	@Override
	public void setParameters(Map<String, String[]> parameters) {
		this.paramMap = parameters;
	}

	@Override
	public void setRequest(Map<String, Object> request) {
		this.reqMap = request;
	}

}
