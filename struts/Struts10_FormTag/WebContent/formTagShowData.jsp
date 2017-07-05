<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
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

	<h3>★★★★★★★专门用于回显的表单页面★★★★★★★</h3>

	<!-- action属性用于生成提交表单的目标URL地址，加上/后可以自动生成Web应用虚拟路径 -->
	<!-- method属性用于指定提交表单的请求方式，默认为post，所以通常可以省略 -->
	<s:form action="/UserAction" method="post">
		
		<!-- 生成单行文本框 -->
		<!-- label属性用于生成单行文本框旁边的说明文字 -->
		<s:textfield name="userName" label="用户名"/>
		<!-- 用户名<input type="text" name="userName"/> -->
		
		<!-- 密码框架 -->
		<s:password name="userPwd" showPassword="true" label="密码"/>
		
		<!-- 多行文本框 -->
		<s:textarea name="describe" label="自我介绍"/>
		
		<!-- 表单隐藏域 -->
		<s:hidden name="userId"/>
		
		<!-- ○单选框 -->
		<s:radio name="season" 
				 list="seasonList" 
				 listKey="seasonId" 
				 listValue="seasonLable"
				 label="季节"/>
		
		<!-- ○多选框 -->
		<% Map<String,String> map = new HashMap<String,String>(); %>
		<% map.put("German","德国"); %>
		<% map.put("Holland","荷兰"); %>
		<% map.put("Brazil","巴西"); %>
		<% map.put("China","中国"); %>
		<% request.setAttribute("checkBoxMap", map); %>
		<s:checkboxlist list="#request.checkBoxMap" name="team" label="你认为哪支队伍能够夺冠"/>
		
		<!-- ○下拉列表 -->
		<s:select name="favoriteSeason"
				  list="#request.seasonList"
				  listKey="seasonId" 
				  listValue="seasonLable"
				  label="你喜欢的季节">
		</s:select>
		
		<!-- 提交按钮 -->
		<!-- value属性生成按钮上显示的文字 -->
		<s:submit value="注册"/>
		
	</s:form>

</body>
</html>