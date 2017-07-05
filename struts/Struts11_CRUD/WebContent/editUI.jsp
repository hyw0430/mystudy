<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	table,td {
		border: 1px solid black;
	}
</style>
</head>
<body>
	<s:debug/>
	
	<s:form action="/updateBook">
		
		<s:hidden name="bookId"/>
		<s:textfield name="bookName" label="书名"/>
		<s:textfield name="author" label="作者"/>
		<s:textfield name="price" label="价格"/>
		
		<s:submit value="更新"/>
	
	</s:form>

</body>
</html>