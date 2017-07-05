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
	
	<!-- 判断请求域中是否有数据 -->
	<s:if test="#request.bookList == null || #request.bookList.empty">
		当前没有数据可以显示
	</s:if>
	<s:else>
		<table>
			<tr>
				<th>ID</th>
				<th>书名</th>
				<th>作者</th>
				<th>价格</th>
				<th>编辑</th>
				<th>删除</th>
			</tr>
		<s:iterator value="#request.bookList">
			<!-- 在遍历过程中，拿到的每一个Book对象，都会被临时放到栈顶 -->
			<tr>
				<td>
					<!-- 获取栈顶对象的bookId -->
					<s:property value="[0].bookId"/>
				</td>
				<td>
					<!-- 获取栈顶对象的bookId -->
					<s:property value="bookName"/>
				</td>
				<td>
					<!-- 获取栈顶对象的bookId -->
					<s:property value="author"/>
				</td>
				<td>
					<!-- 获取栈顶对象的bookId -->
					<s:property value="price"/>
				</td>
				<td>
					<s:a action="editUI?bookId=%{[0].bookId}">编辑</s:a>
				</td>
				<td>
					<s:a action="removeBook?bookId=%{[0].bookId}">删除</s:a>
				</td>
			</tr>
		</s:iterator>
		</table>
	</s:else>
	
	<br /><br />
	
	<s:a value="/addUI.jsp">添加图书</s:a>

</body>
</html>