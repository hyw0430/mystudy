package com.atguigu.helloworld.action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 处理Struts2请求的Action类，类似于Servlet
 * 在我们这个HelloWorld中，当前Action类需要由三方面的功能
 * 		1.接收表单提交的请求参数：使用与表单元素的name属性值对应的setXxx()方法即可
 * 		2.将请求转发到detail.jsp：通过saveBook()的String类型的返回值即可指定
 * 		3.在detail.jsp页面上显示表单数据：使用getXxx()方法即可
 * @author Creathin
 *
 */
public class BookAction extends ActionSupport{
	
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	public BookAction() {
		System.out.println("Struts2的Action对象创建了！每一个请求都会创建一个对象！");
	}
	
	private String bookName;
	private String author;
	private double price;
	
	/**
	 * Action方法，用于具体处理Struts2请求，类似于Servlet中的doGet()、doPost()方法
	 * @return
	 */
	public String saveBook() {
		System.out.println("Action方法执行了……");
		return "save-success";
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
