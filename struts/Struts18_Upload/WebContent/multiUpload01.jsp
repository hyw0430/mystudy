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
	<center>
		<s:actionerror/>
		<!-- 多个文件上传的表单 -->
		<s:form action="multiUploadAction01" enctype="multipart/form-data">
			
			<s:file name="logo" label="选择文件"/>
			<s:file name="facePicture" label="选择文件"/>
			<s:submit value="上传"/>
		
		</s:form>
	</center>

</body>
</html>