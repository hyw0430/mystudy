<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="com.atguigu.spring.Person"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<a href="helloworld">helloworld</a>
	<br><br>
	
	<!-- 获取 IOC 容器, 在从 IOC 容器中获取 IOC 容器中配置的 bean 实例. -->
	<% 
		//很少需要获取 IOC 容器的引用, 了解.
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(application);
	
		Person person = ctx.getBean(Person.class);
		out.print("person#lastName: " + person.getLastName());
	%>
	
</body>
</html>