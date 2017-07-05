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
<!-- s:a生成超链接的标签 -->
<center><p>==========实验==========</p></center>
<p>●实验①	在value属性中使用“/”开头的绝对路径</p>
<s:a value="targetUrl">Target</s:a>
<!-- <a href="targetUrl">Target</a> -->

<s:a value="/targetUrl">Target</s:a>
<!-- <a href="/Tag/targetUrl">Target</a> -->

<p>●实验②	附加普通字符串请求参数</p>
<s:a value="/targetUrl?userName=Tom2015">Target</s:a>
<!-- <a href="/Tag/targetUrl?userName=Tom2015">Target</a> -->

<p>●实验③	附加从值栈中取出的值作为请求参数</p>
<s:a value="/targetUrl?userName=%{[0].subject}">Target</s:a>
<!-- <a href="/Tag/targetUrl?userName=JavaEE">Target</a> -->

</body>
</html>