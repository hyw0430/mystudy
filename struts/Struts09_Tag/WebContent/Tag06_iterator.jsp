<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
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
<!-- s:iterator标签作用是迭代数组或集合 -->
<center><p>==========实验==========</p></center>
<p>●实验①	遍历List集合</p>
<% List<String> strList = Arrays.asList("AAA","KKK","QQQ","JJJ"); %>
<% request.setAttribute("strList", strList); %>
<!-- 使用value属性通过OGNL表达式指定要遍历的集合 -->
<!-- 遍历得到的每一个元素都会被临时压入栈顶 -->
<s:iterator value="#request.strList">
	<s:property/><br />
</s:iterator>
<br />
<!-- 如果要遍历的集合在栈顶，则可以省略value属性 -->
<s:push value="#request.strList">
	<s:iterator>
		<s:property/><br />
	</s:iterator>
</s:push>

<br />
<!-- 使用var属性可以将遍历得到的数据临时保存到Map栈中 -->
<s:iterator value="#request.strList" var="str">
	<s:property value="#str"/><br />
</s:iterator>

<s:debug/>
<br />

<p>●实验②	遍历Map集合</p>
<%
	Map<String,String> map = new HashMap<String,String>();
	map.put("key01", "val01");
	map.put("key02", "val02");
	map.put("key03", "val03");
	map.put("key04", "val04");
	map.put("key05", "val05");
	request.setAttribute("map", map);
%>
<s:iterator value="#request.map">
	<s:property/>&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="key"/>&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="value"/><br />
</s:iterator>

<p>●实验③	在偶数个元素的后面加@</p>
<s:iterator value="#request.strList" status="myStatus">
	
	<s:property/>&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="#attr.myStatus"/>
	<s:if test="#attr.myStatus.count%2 == 0">@</s:if>
	<s:if test="#attr.myStatus.odd">%</s:if>
	<br />
	
</s:iterator>

</body>
</html>