package com.atguigu.filter.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.filter.bean.Book;

public class BookFilter extends HttpFilter{

	@Override
	public void doFilter(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//1.获取当前请求要访问的目标资源地址
		String servletPath = request.getServletPath();
		System.out.println("servletPath="+servletPath);
		
		//2.根据目标资源地址决定如何处理请求
		//①如果目标资源是表单页面，则跳转到表单页面
		// servletPath=/saveUI.action
		if("/saveUI.action".equals(servletPath)) {
			
			request.getRequestDispatcher("/saveUI.jsp").forward(request, response);
			
		}
		
		//②如果当前请求要提交表单数据，则接收表单数据
		// servletPath=/saveBook.action
		if("/saveBook.action".equals(servletPath)) {
			//[1]接收请求参数
			String bookName = request.getParameter("bookName");
			String author = request.getParameter("author");
			String priceStr = request.getParameter("price");
			
			//[2]将价格数据转换为double类型
			double price = Double.parseDouble(priceStr);
			
			//[3]将请求参数封装为Book对象
			Book book = new Book(null, bookName, author, price);
			
			//[4]将Book对象保存到请求域中
			request.setAttribute("book", book);
			
			//[5]将请求转发到detail.jsp
			request.getRequestDispatcher("/detail.jsp").forward(request, response);
		}
	}

}
