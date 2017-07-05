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
		<h2>
			<a href="${pageContext.request.contextPath }/resultType_dispatcher.action">转发到result.jsp</a>
		</h2>
		<h2>
			<a href="${pageContext.request.contextPath }/resultType_redirect.action">重定向到result.jsp</a>
		</h2>
		<h2>
			<a href="${pageContext.request.contextPath }/resultType_redirectToAction.action">重定向到目标Action</a>
		</h2>
		<h2>
			<a href="${pageContext.request.contextPath }/resultType_redirectDirect.action">直接重定向到目标Action</a>
		</h2>
		<h2>
			<a href="${pageContext.request.contextPath }/resultType_dispatcherToAction.action">转发到目标Action</a>
		</h2>
	</center>

</body>
</html>