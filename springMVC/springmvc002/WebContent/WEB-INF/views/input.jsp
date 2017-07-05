<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
	<form action="${pageContext.request.contextPath}/empConvert" method="post">
		<input type="text" name="employee" value="li4;li4@163.com;1;105"/><br>
		<input type="submit" value="empConvert_commit">
	</form>
<hr>
<body>
	<form:form action="${pageContext.request.contextPath}/emp" method="post" modelAttribute="employee">
		lastName:<form:input path="lastName"/>&nbsp;&nbsp;<form:errors path="lastName"></form:errors><br>
		email:<form:input path="email"/>&nbsp;&nbsp;<form:errors path="email"></form:errors><br>
		gender:<form:radiobuttons path="gender" items="${requestScope.genders}"/><br>
		department:<form:select path="department.id" items="${requestScope.departments}"
					itemLabel="departmentName" itemValue="id"></form:select><br>
		birth:<form:input path="birth"/><br>
		salary:<form:input path="salary"/><br>
		<input type="submit" value="saveEmployee">
	</form:form>
</body>
</html>