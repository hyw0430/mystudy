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

	<form action="${pageContext.request.contextPath }/MarryAction" method="post">
		
		婚否<input type="radio" name="married" value="true"/>已婚
		<input type="radio" name="married" value="false"/>未婚
		<input type="submit" value="提交"/>
		
	</form>

	<form action="${pageContext.request.contextPath }/MarryAction" method="post">
		
		婚否<input type="checkbox" name="married" value="true"/>已婚/未婚
		<input type="submit" value="提交"/>
		
	</form>

	<s:form action="/MarryAction">
		
		<s:checkbox name="married" label="已婚/未婚"/>
		<s:submit value="提交"/>
		
	</s:form>

</body>
</html>