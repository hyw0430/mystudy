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
<!-- s:set标签作用是向属性域中存入数据 -->
<center><p>==========实验==========</p></center>
<p>●实验①	将attrName01="attrValue01"存入Session域</p>
<s:set var="attrName01" value="'attrValue01'" scope="session"/>
<%=session.getAttribute("attrName01") %>

<p>●实验②	将attrName02="attrValue02"存入域对象，不指定范围，以测试default对应的范围</p>
<s:set var="attrName02" value="'attrValue02'"/>
<%=pageContext.getAttribute("attrName02") %><br />
<%=request.getAttribute("attrName02") %><br />
<%=session.getAttribute("attrName02") %><br />
<%=application.getAttribute("attrName02") %><br />

<p>●实验③	以attrName03为键，以OGNL表达式为值，存入域对象</p>
<s:set var="attrName03" value="subject"/>
<%=pageContext.getAttribute("attrName03") %>

</body>
</html>