<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<p><a href="${pageContext.request.contextPath }/Tag01_property.action">Test Tag01</a></p>
	<p><a href="${pageContext.request.contextPath }/Tag02_URL.action">Test Tag02</a></p>
	<form action="${pageContext.request.contextPath }/Tag02_URL.action?age=20" method="post">
		<input type="text" name="userName" />
		<input type="submit" />
	</form>
	<p><a href="${pageContext.request.contextPath }/Tag03_set.action">Test Tag03</a></p>
	<p><a href="${pageContext.request.contextPath }/Tag04_push.action">Test Tag04</a></p>
	<p><a href="${pageContext.request.contextPath }/Tag05_if.action">Test Tag05</a></p>
	<p><a href="${pageContext.request.contextPath }/Tag06_iterator.action">Test Tag06</a></p>
	<p><a href="${pageContext.request.contextPath }/Tag07_sort.action">Test Tag07</a></p>
	<p><a href="${pageContext.request.contextPath }/Tag08_a.action">Test Tag08</a></p>
	
</body>
</html>