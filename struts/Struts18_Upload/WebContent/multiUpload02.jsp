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
	<center>
		<!-- 多个文件上传的表单 -->
		<!-- 如果发生了某一个字段的错误，两个文件上传框都会显示错误消息 -->
		<!-- 解决：使用simple主题，用s:fielderror标签显示错误消息 -->
		<s:fielderror name="uploadFile"/>
		<s:form action="multiUploadAction02" theme="simple" enctype="multipart/form-data">
			
			选择文件<s:file name="uploadFile" label="选择文件"/><br/>
			选择文件<s:file name="uploadFile" label="选择文件"/><br/>
			<s:submit value="上传"/>
		
		</s:form>
	</center>

</body>
</html>