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

	<s:a action="UserAction?userName=Tom2015">To UserAction</s:a>
	
	<br /><br />
	
	<s:a action="BookAction?userName=Tom2015">To BookAction</s:a>

</body>
</html>