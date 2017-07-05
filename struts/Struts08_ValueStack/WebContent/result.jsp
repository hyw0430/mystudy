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
		<h1>Result</h1>
		
		<p>
			通过Action类的getXxx()方法读取到的数据：${requestScope.happyMessage }
			<br />
			<%=request.getAttribute("happyMessage") %>
		</p>
		
		<p>
			在Struts2环境下的request对象：<%=request %>
		</p>
		
	</center>
	
</body>
</html>