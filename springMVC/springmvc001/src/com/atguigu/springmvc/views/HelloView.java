package com.atguigu.springmvc.views;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.View;

@Component
public class HelloView implements View
{
	@Override
	public String getContentType()
	{
		return "text/html; charset=UTF-8";
	}

	@Override
	public void render(Map<String, ?> model, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		
		response.getWriter().write("hello,0719 View test");
		response.getWriter().flush();
		response.getWriter().close();
	}
	
}
