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

	<s:form action="/Conversion06">
	
		<s:textfield name="empList[0].empName" label="员工姓名01"/>
		<s:textfield name="empList[0].address" label="地址01" />
	
		<s:textfield name="empList[1].empName" label="员工姓名02"/>
		<s:textfield name="empList[1].address" label="地址02" />
	
		<s:textfield name="empList[2].empName" label="员工姓名03"/>
		<s:textfield name="empList[2].address" label="地址03" />
		
		<s:submit value="提交"/>
	
	</s:form>

</body>
</html>