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

	<!-- 在页面上实现国际化：填充字符串的占位符 -->
	<s:text name="today">
		<s:param value="'Very Good Day'"/>
	</s:text>
	
	<br />
	
	<!-- 创建日期对象 -->
	<s:bean name="java.util.Date" var="myDate"/>
	
	<s:text name="today">
		<s:param value="#attr.myDate"/>
	</s:text>
	
</body>
</html>