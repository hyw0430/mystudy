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
	<s:actionerror/>

	<s:form action="tokenAction">
		<!-- 使用s:token标签生成唯一值并保存到Session域和表单隐藏域中 -->
		<s:token/>
		<s:textfield name="userName" label="用户名"/>
		<s:submit value="提交"/>
		
	</s:form>
	
	<br /><br />

	<s:form action="tokenSessionAction">
		<!-- 使用s:token标签生成唯一值并保存到Session域和表单隐藏域中 -->
		<s:token/>
		<s:textfield name="userName" label="用户名"/>
		<s:submit value="提交"/>
		
	</s:form>
	
	<s:debug/>
	
	<s:property value="#session['struts.tokens.token']"/>

</body>
</html>