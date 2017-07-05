<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<% session.setAttribute("sessAttrName", "sessAttrValue"); %>
<% session.setAttribute("special-name", "special-value"); %>

<center>
<a href="${pageContext.request.contextPath }/ValueStackAction_testGetProperty.action">测试1：通过requestScope.属性名方式读取数据</a><br /><br />
<a href="${pageContext.request.contextPath }/ValueStackAction_testGetValueStack.action">测试2：获取ValueStack对象</a><br /><br />
<a href="${pageContext.request.contextPath }/ValueStackAction_testPush.action">测试3：将测试数据压入对象栈栈顶</a><br /><br />
<a href="${pageContext.request.contextPath }/ValueStackAction_testMapStack.action">测试4：Map栈的本质</a><br /><br />
<a href="${pageContext.request.contextPath }/ValueStackAction_testMapStackValue.action">测试5：向请求域中保存数据，在Map栈中查看</a><br /><br />
<a href="${pageContext.request.contextPath }/ValueStackAction_testReadObjectStack01.action">测试6：OGNL：[0].属性名的方式从栈顶开始查找对象</a><br /><br />
<a href="${pageContext.request.contextPath }/ValueStackAction_testReadObjectStack02.action">测试7：OGNL：[索引].属性名的方式从索引值指定的位置开始查找对象</a><br /><br />
<a href="${pageContext.request.contextPath }/ValueStackAction_testReadObjectStack03.action">测试8：OGNL：不指定索引时表示默认从栈顶读取数据</a><br /><br />
<a href="${pageContext.request.contextPath }/ValueStackAction_testReadMapStack01.action?userName=Tom2020">测试9：使用OGNL表达式从Map栈中读取请求参数</a><br /><br />
<a href="${pageContext.request.contextPath }/ValueStackAction_testReadMapStack02.action">测试10：使用OGNL表达式从Map栈中读取request域中的数据</a><br /><br />
<a href="${pageContext.request.contextPath }/ValueStackAction_testReadMapStack03.action">测试11：使用OGNL表达式从Map栈中读取session域中的数据</a><br /><br />
<a href="${pageContext.request.contextPath }/ValueStackAction_testReadMapStack04.action">测试12：使用OGNL表达式从Map栈中读取application域中的数据</a><br /><br />
<a href="${pageContext.request.contextPath }/ValueStackAction_testReadMapStack05.action">测试13：使用OGNL表达式从各个域对象中按从小到大的顺序读取数据</a><br /><br />
<a href="${pageContext.request.contextPath }/ValueStackAction_testReadMapStack06.action">测试14：使用OGNL表达式从Map栈中使用带特殊符号的键读取数据</a><br /><br />
</center>

</body>
</html>