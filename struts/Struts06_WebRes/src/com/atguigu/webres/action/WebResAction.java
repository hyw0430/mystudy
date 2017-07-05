package com.atguigu.webres.action;

import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;

import com.opensymphony.xwork2.ActionContext;

public class WebResAction {
	
	public String getRequestMap() {
		
		ActionContext context = ActionContext.getContext();
		
		//通过ActionContext获取封装request域数据的Map
		Object reqMap = context.get("request");
		
		Map<String,Object> rm = (Map<String, Object>) reqMap;
		
		rm.put("reqName", "reqValue");

		return "success";
	}
	
	public String getParamMap() {
		
		//通过ActionContext获取封装请求参数的Map
		ActionContext context = ActionContext.getContext();
		
		Map<String, Object> parameters = context.getParameters();
		
		Object userName = parameters.get("userName");
		System.out.println("userName="+userName);
		
		//本质上是字符串数组类型
		String [] paramArr = (String[]) userName;
		
		System.out.println("用户名="+paramArr[0]);
		
		//如果向这个封装请求参数数据的Map中添加数据，能不能创建新的请求参数？
		//不能，但添加了也没有报错
		parameters.put("subject", "JavaEE");
		
		return "success";
	}
	
	public String getSessionMap() {
		
		//通过ActionContext获取封装session域数据的Map
		//1.获取ActionContext对象
		ActionContext actionContext = ActionContext.getContext();
		
		//2.获取封装session域数据的Map
		Map<String, Object> session = actionContext.getSession();
		
		//3.向Map中保存数据
		session.put("sessName", "sessValue");
		
		//4.封装session域数据的Map有一个特殊功能：可以将Session对象强制失效
		//org.apache.struts2.dispatcher.SessionMap这个类中维护了一个原始的Session对象
		System.out.println(session.getClass().getName());
		
		//通过invalidate()方法可以将Session对象强制失效
		SessionMap<String, Object> sm = (SessionMap<String, Object>) session;
		sm.invalidate();
		
		return "success";
	}
	
	public String getAppMap() {
		
		//通过ActionContext获取封装application域数据的Map
		//1.获取ActionContext对象
		ActionContext actionContext = ActionContext.getContext();
		
		//2.获取封装application域数据的Map对象
		Map<String, Object> application = actionContext.getApplication();
		
		//3.将数据保存到Map对象中，到结果页面上从application域读取，从而证明这个Map确实是封装application域数据的Map
		application.put("appName", "appValue");
		
		return "success";
	}

}
