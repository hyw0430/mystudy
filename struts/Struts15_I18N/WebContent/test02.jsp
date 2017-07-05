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

	<s:debug/>
	
	<!-- 在页面上实现国际化：使用label属性 -->

	<s:form action="/#">
	
		<s:textfield name="usreName" label="%{[0].getText('userName')}"/>
		<s:password name="password" label="%{[0].getText('password')}"/>
		<s:submit value="%{[0].getText('login')}"/>
	
	</s:form>

</body>
</html>