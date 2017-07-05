<%@page import="com.atguigu.filter.bean.BookComparator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.atguigu.filter.bean.Book"%>
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
<!-- sort标签可以对集合进行排序 -->
<!-- TreeSet -->
<!-- 自然排序：集合元素需要实现Comparable接口 -->
<!-- 定制排序：创建TreeSet集合对象时需要传入Compartor比较器对象 -->
<center><p>==========实验==========</p></center>
<p>●实验	分别按照bookName、author、price对Book List进行排序</p>
<%
	List<Book> bookList = new ArrayList<Book>();
	bookList.add(new Book(1,"book05","author01",100));
	bookList.add(new Book(2,"book27","author02",100));
	bookList.add(new Book(3,"book01","author03",100));
	bookList.add(new Book(4,"book13","author04",100));
	bookList.add(new Book(5,"book06","author05",100));
	request.setAttribute("bookList", bookList);
%>
<s:iterator value="#request.bookList">
	<s:property/><br />
</s:iterator>
<br />
<!-- 通过comparator属性指定比较器对象 -->
<!-- 通过source属性指定被排序的集合 -->
<!-- var属性：排序后的集合保存到域对象中时使用的属性名 -->
<% BookComparator bc = new BookComparator(); %>
<% request.setAttribute("comparator", bc); %>
<s:sort comparator="#request.comparator" source="#request.bookList" var="sortedList"/>
<s:iterator value="#attr.sortedList">
	<s:property/><br />
</s:iterator>
<br />

<s:sort comparator="#request.comparator" source="#request.bookList">
	<s:iterator>
		<s:property/><br />
	</s:iterator>
</s:sort>

</body>
</html>