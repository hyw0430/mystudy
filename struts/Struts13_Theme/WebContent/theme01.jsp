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

	<!-- 表单级别设置主题 -->
	<s:form action="abc" theme="xhtml">
		<!-- 标签级别设置主题 -->
		<s:textfield label="UserName" name="userName" theme="simple"></s:textfield>
		<s:password label="PawwWord" name="userPwd"></s:password>
		<s:submit value="EEE" />
	</s:form>

	<!-- 域对象中设置主题 -->
	<% session.setAttribute("theme", "xhtml"); %>

	<s:form action="abc">
		<s:textfield label="UserName" name="userName"></s:textfield>
		<s:password label="PawwWord" name="userPwd"></s:password>
		<s:submit value="EEE" />
	</s:form>

</body>
</html>