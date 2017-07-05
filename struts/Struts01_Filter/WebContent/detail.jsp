<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.centerText {text-align: center;}
	.textStyle {border: 1px solid black;}
	.btnStyle {border: 1px solid black;width:150px;}
	.btnStyle:hover {border: 1px solid black;width:150px;background-color: white;}
	.tableStyle {border: 1px solid black;border-collapse: collapse;margin: 50px auto 0px auto;}
	.tableStyle td {border: 1px solid black;padding: 5px;}
</style>
</head>
<body>

	<table class="tableStyle">
		<tr>
			<td colspan="2" class="centerText" id="tableHeader">显示图书信息</td>
		</tr>
		<tr>
			<td>图书名称：</td><td>${requestScope.book.bookName }</td>
		</tr>
		<tr>
			<td>图书作者：</td><td>${requestScope.book.author }</td>
		</tr>
		<tr>
			<td>图书价格：</td><td>${requestScope.book.price }</td>
		</tr>
		<tr>
			<td colspan="2" class="centerText"><a href="${pageContext.request.contextPath }/index.jsp">回首页</a></td>
		</tr>
	</table>

</body>
</html>