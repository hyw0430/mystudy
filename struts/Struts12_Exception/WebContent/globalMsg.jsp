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
	<center>
		<h3>Global Good</h3>
		<h3>
			<s:property value="[0].exception"/>
		</h3>
		<s:a value="/makeException.jsp">回makeException.jsp</s:a>
	</center>

</body>
</html>