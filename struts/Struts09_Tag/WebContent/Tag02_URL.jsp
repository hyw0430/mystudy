<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- <script type="text/javascript" src="/Tag/script/jQuery.js"></script> -->
<script type="text/javascript" src="<s:url value='/script/jQuery.js'/>"></script>
</head>
<body>
<s:debug/>
<center><p>==========实验==========</p></center>
<!-- s:url标签：作用是生成URL地址字符串 -->
<p>●实验①	通过value属性创建URL地址字符串</p>
<s:url value="targetUrl"/><br />
<s:url value="/targetUrl"/>
<%-- 加上斜杠后，自动在路径前面加上当前Web应用的虚拟路径，相当于：${pageContext.request.contextPath } --%>

<p>●实验②	通过action属性创建URL地址字符串</p>
<!-- 使用action属性，指定一个action的name值，可以直接生成访问这个目标Action的url地址 -->
<s:url action="saveBook"/><br />
<!-- /Tag/saveBook.action -->

<p>●实验③	通过action属性和namespace属性以及method属性创建URL地址字符串</p>
<s:url action="updateBook" namespace="/bookShop" method="editBook"/>
<!-- /Tag/bookShop/updateBook!editBook.action -->

<p>●实验④	创建带请求参数的URL地址字符串</p>
<s:url action="removeBook">
	<s:param name="bookId" value="150"/>
</s:url>
<!-- /Tag/removeBook.action?bookId=150 -->

<p>●实验⑤	请求参数的值来源于值栈</p>
<s:url action="saveBook">
	<!-- 在value属性值直接写OGNL表达式 -->
	<s:param name="bookName" value="[0].subject"/>
</s:url>
<!-- /Tag/saveBook.action?bookName=JavaEE -->

<p>●实验⑥	以字符串方式指定请求参数的值，但又不让它进行OGNL解析</p>
<s:url action="saveBook">
	<!-- 在value属性值直接写OGNL表达式 -->
	<s:param name="bookName" value="'subject'"/>
</s:url>

<p>●实验⑦	创建包含GET方式传递过来的请求参数的URL地址字符串</p>
<s:url action="showBookList" includeParams="get"/>
<!-- /Tag/showBookList.action?age=20 -->

<p>●实验⑧	创建包含以任何方式传递过来的请求参数的URL地址字符串</p>
<s:url action="showBookList" includeParams="all"/>
<!-- /Tag/showBookList.action?age=20&userName=Tom2015 -->

<p>●实验⑨	创建不包含contextPath的URL地址字符串</p>
<s:url action="saveBook" includeContext="false"/>
<!-- /saveBook.action -->

</body>
</html>