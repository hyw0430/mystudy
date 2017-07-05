<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.9.1.min.js"></script>

<script type="text/javascript">
	$(function(){
		$("#testJson").click(function(){
			var url = $(this).attr("href");
			var args = {name:"z3"};
			$.post(url,args,function(data){
				for(var i=0;i<data.length;i++)
				{
					var id = data[i].id;
					var lastName = data[i].lastName;
					alert(id+"  "+lastName);
				}
			})
			return false;
		})
	})
</script>
<body>
testInit:<a href="${pageContext.request.contextPath}/testInit">testInit</a>
<hr>
show all employees:<a href="${pageContext.request.contextPath}/emps">show all employees</a>
<hr>
testJson:<a id="testJson" href="${pageContext.request.contextPath}/testJson">testJson</a>
<hr>
testRequestBody:<br>
<form action="${pageContext.request.contextPath}/testRequestBody" method="post" enctype="multipart/form-data">
	file:<input type="file" name="file1" value=""><br>
	password:<input type="password" name="password" value=""><br>
	note:<input type="text" name="note" value=""><br>
	<input type="submit" value="testRequestBody_commit">
</form>
<hr>
testDownLoad:<a href="${pageContext.request.contextPath}/testDownLoad">testDownLoad</a>
<hr>
i18n:<a href="${pageContext.request.contextPath}/i18n">i18n</a>
<hr>
i18n2:<a href="${pageContext.request.contextPath}/i18n2">i18n2</a>
<hr>
i18n3:<br>
<a href="${pageContext.request.contextPath}/i18n3?locale=zh_CH">中文</a><br>
<a href="${pageContext.request.contextPath}/i18n3?locale=en_US">English</a><br>
</body>
</html>