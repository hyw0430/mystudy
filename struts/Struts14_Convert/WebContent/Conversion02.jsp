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

	<s:form action="/Conversion02">
	
		<s:textfield name="address.country" label="COUNTRY"/>	
		<s:textfield name="address.province" label="PROVINCE"/>	
		<s:textfield name="address.city" label="CITY"/>	
		<s:textfield name="address.street" label="STREET"/>
		
		<s:submit value="提交"/>
	
	</s:form>

</body>
</html>