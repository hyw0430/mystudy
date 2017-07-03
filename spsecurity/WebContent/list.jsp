<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h4>List Page</h4>
	
	<!--  
		利用 property 属性可以访问用户登陆后的信息. 
	-->
	<%-- 
	Welcome: <security:authentication property="principal.username"></security:authentication>
	<br><br>
	--%>
	
	<!-- 可以利用 security:authorize 标签来隐藏受保护的资源 -->
	<security:authorize ifAllGranted="ROLE_ADMIN">
		<a href="admin.jsp">To Admin Page</a>
		<br><br>
	</security:authorize>
	
	<security:authorize ifAnyGranted="ROLE_ADMIN,ROLE_USER">
		<a href="user.jsp">To User Page</a>
		<br><br>
	</security:authorize>	
	
	
	<a href="security-logout">Logout</a>
	
	<%-- 
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(application);
		Object object = ctx.getBean("springSecurityFilterChain");
		
		System.out.println(object);
		System.out.println(object instanceof Filter);
	--%>
	
</body>
</html>