<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<center>
		<p>
			<s:text name="userName"/>
		</p>
		<p>
			<s:text name="password"/>
		</p>
		<p>
			<s:text name="login"/>
		</p>
		
		<p>
			<s:a action="toLoginPage?request_locale=en_US">English</s:a>
			|
			<s:a action="toLoginPage?request_locale=zh_CN">中文</s:a>
		</p>
		
		<p>
			<s:a value="/index.jsp">回首页</s:a>
		</p>
		
	</center>

</body>
</html>