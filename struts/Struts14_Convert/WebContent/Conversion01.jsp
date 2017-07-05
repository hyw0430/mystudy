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
	
	<!-- 使用s:fieldError标签直接显示错误消息 -->
	<!-- fieldName属性用于指定要显示错误消息的字段名称 -->
	<s:fielderror fieldName="age"/>
	
	<!-- 通过OGNL表达式读取字段中的错误消息 -->
	<s:property value="[0].fieldErrors.age[0]"/>

	<s:form action="/Conversion01" theme="simple">
	
		<s:textfield name="age" label="年龄"/>
		<s:submit value="提交"/>
	
	</s:form>

</body>
</html>