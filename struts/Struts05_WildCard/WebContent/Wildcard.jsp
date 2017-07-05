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
		<h3><a href="${pageContext.request.contextPath }/bookAction_saveBook.action">保存图书</a></h3>
		<h3><a href="${pageContext.request.contextPath }/bookAction_removeBook.action">删除图书</a></h3>
		<h3><a href="${pageContext.request.contextPath }/bookAction_editBook.action">编辑图书</a></h3>
		<h3><a href="${pageContext.request.contextPath }/bookAction_queryBook.action">查询图书</a></h3>
	</center>

</body>
</html>