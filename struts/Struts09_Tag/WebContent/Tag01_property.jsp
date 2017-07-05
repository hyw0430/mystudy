<%@page import="com.atguigu.valuestack.bean.Person"%>
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
	<s:debug/>

	<center><p>==========实验==========</p></center>
	<!-- s:property标签的作用是执行OGNL表达式 -->
<p>●实验①	读取对象栈中对象的属性值</p>
<s:property value="[0].subject"/><br />
<s:property value="subject"/><br />

<p>●实验②	访问Map栈，读取Session域中对象的属性值</p>
<% Person person = new Person("PersonGood"); %>
<% session.setAttribute("person", person); %>
<s:property value="#session.person.personName"/><br />

<p>●实验③	访问Map栈，通过attr读取域对象中的属性值</p>
<%-- pageContext.setAttribute("attrName", "pageValue"); --%>
<%-- request.setAttribute("attrName", "reqValue"); --%>
<%-- session.setAttribute("attrName", "sessValue"); --%>
<% application.setAttribute("attrName", "appValue"); %>
<s:property value="#attr.attrName"/><br />

<p>●实验④	访问Map栈，读取请求参数的值</p>
<s:property value="#parameters.userName[0]"/><br />
<s:property value="#parameters.userName"/><br />

<p>●实验⑤	不指定value属性，返回栈顶对象</p>
<s:property/>

<p>●实验⑥	通过value属性值找不到数据，使用default属性的值</p>
<s:property value="notExists" default="DefaultValue"/>

</body>
</html>