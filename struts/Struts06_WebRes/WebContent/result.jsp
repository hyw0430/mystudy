<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<center>
		<h1>Result</h1>
		
		<h2>
			appName:<%=application.getAttribute("appName") %>
		</h2>
		
		<h2>
			sessName:<%=session.getAttribute("sessName") %>
		</h2>
		
		<h2>
			subject:<%=request.getParameter("subject") %>
		</h2>
		
		<h2>
			reqName:<%=request.getAttribute("reqName") %>
		</h2>
	</center>

</body>
</html>