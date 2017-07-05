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
	
	<!-- 在页面上实现国际化：使用key属性 -->

	<s:form action="/#">
	
		<s:textfield name="usreName" key="userName"/>
		<s:password name="password" key="password"/>
		<s:submit key="login"/>
	
	</s:form>

</body>
</html>