<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<center>
		<h3>
			<a href="${pageContext.request.contextPath }/getAppMap.action">通过ActionContext获取封装application域数据的Map</a>
		</h3>
		<h3>
			<a href="${pageContext.request.contextPath }/getSessionMap.action">通过ActionContext获取封装session域数据的Map</a>
		</h3>
		<h3>
			<a href="${pageContext.request.contextPath }/getParamMap.action?userName=Tom2015">通过ActionContext获取封装请求参数的Map</a>
		</h3>
		<h3>
			<a href="${pageContext.request.contextPath }/getRequestMap.action">通过ActionContext获取封装request域数据的Map</a>
		</h3>
		<h3>
			<a href="${pageContext.request.contextPath }/testXxxAware.action">通过实现XxxAware接口获取Web资源对应的Map</a>
		</h3>
		<h3>
			<a href="${pageContext.request.contextPath }/testServletActionContext.action">通过ServletActionContext获取原生的Web资源对象</a>
		</h3>
		<h3>
			<a href="${pageContext.request.contextPath }/testServletXxxAware.action">通过实现ServletXxxAware接口获取原生的Web资源对象</a>
		</h3>
	</center>

</body>
</html>