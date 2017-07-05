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
	
		<s:form action="/test03">
		
			<!-- 对email字段同时使用“字符串长度”和“Email格式”两种验证规则 -->
			<s:textfield name="email" label="Email" />
			
			<s:submit value="提交" />
		
		</s:form>
		
	</center>

</body>
</html>