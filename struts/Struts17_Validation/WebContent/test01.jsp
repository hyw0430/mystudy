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

	<s:debug />

	<center>
	
		<!-- 默认主题 -->
		<s:form action="/test01">
		
			<s:textfield name="age" label="年龄" />
			<s:submit value="提交" />
		
		</s:form>
		
		<!-- simple主题 -->
		<s:fielderror fieldName="age"/>
		<s:form action="/test01" theme="simple">
		
			年龄<s:textfield name="age" />
			<s:submit value="提交" />
		
		</s:form>
	
	</center>

</body>
</html>