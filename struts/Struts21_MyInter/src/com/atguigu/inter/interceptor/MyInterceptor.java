package com.atguigu.inter.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ValidationAware;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class MyInterceptor extends AbstractInterceptor{

	private static final long serialVersionUID = 1L;
	
	private String interceptParam;
	
	public void setInterceptParam(String interceptParam) {
		this.interceptParam = interceptParam;
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		//0.打印拦截器参数
		System.out.println("interceptParam="+interceptParam);
		
		//1.获取目标Action对象
		Object action = invocation.getAction();
		System.out.println(action);
		
		//2.获取Web资源对象
		ActionContext actionContext = invocation.getInvocationContext();
		Map<String, Object> parameters = actionContext.getParameters();
		Object paramObj = parameters.get("userName");
		String [] paramArr = (String[]) paramObj;
		String userName = paramArr[0];
		
		//可以根据一定条件决定是继续执行后续拦截器还是直接执行某个result
		//3.检查请求参数中userName是否等于Tom2015
		if("Tom2015".equals(userName)) {
			//4.如果等于Tom2015则继续执行后续拦截器
			return invocation.invoke();
			
		}else{
			
			if(action instanceof ValidationAware) {
				ValidationAware va = (ValidationAware) action;
				va.addActionError("用户名不正确！");
			}
			
			//5.如果不等于则执行name="input"的result
			return "input";
			
		}
		
	}

}
