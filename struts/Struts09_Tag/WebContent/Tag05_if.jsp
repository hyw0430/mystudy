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
<center><p>==========实验==========</p></center>
<p>●实验①	传入以age为键的请求参数，根据age的不同值测试不同分支条件</p>
<!-- s:if和s:elseif和s:else是用于进行分支流程控制的 -->
<s:if test="#parameters.age[0]<18">未成年</s:if>
<s:elseif test="#parameters.age[0]<30">青年</s:elseif>
<s:elseif test="#parameters.age[0]<50">中年</s:elseif>
<s:elseif test="#parameters.age[0]<60">状年</s:elseif>
<s:else>老年</s:else>

</body>
</html>