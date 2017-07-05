package com.atguigu.CRUD.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.atguigu.CRUD.bean.Book;
import com.atguigu.CRUD.dao.Dao;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;

/**
 * Struts2运行流程中的关键点
 * 	1.Struts2会在执行每一个目标Action方法之前调用指定的拦截器
 *  2.每一个拦截器都负责一项具体的功能，例如：params拦截器负责将请求参数通过setXxx()方法注入到栈顶对象中
 *  3.拦截器执行的顺序是由当前默认拦截器栈中定义的
 *  4.Struts2会在执行所有拦截器之前将目标Action类的对象压入到栈顶
 *问题一：当BookAction类中包含大量冗余代码
 *问题分析：
 *	1.冗余代码是用于注入请求参数的setXxx()方法和getXxx()方法，但所有这些方法都在Book类中已经包含了。
 *	2.setXxx()方法和getXxx()方法起作用和当前类是不是Action类没有关系，关键在于这个类是不是在栈顶。
 *	3.所以如果能够将Book对象也放在栈顶的位置，那么就能够通过Book类中已经有的set、get方法完成任务，从而省略BookAction中的冗余代码。
 *	4.因为注入请求参数是通过params拦截器，所以必须在params拦截器执行之前，将Book对象放入栈顶
 *	5.在params拦截器之前有一个ModelDriven拦截器，专门负责将指定的对象压入栈顶
 *解决方案：
 *	1.实现ModelDriven接口，泛型参数的位置传入当前实体类，也就是Book类
 *	2.这里getModel()需要将Book对象返回，ModelDriven拦截器执行时会将getModel()的返回值压入栈顶
 *	3.创建Book类型的成员变量，在getModel()方法中为其赋值，并返回this.book
 *	4.去掉重复代码，并做必要的调整
 */
public class CopyOfBookAction02 implements RequestAware,ModelDriven<Book>{
	
	private Dao dao = new Dao();
	private Map<String, Object> requestMap;
	
	private Book book;

	@Override
	public Book getModel() {
		this.book = new Book();
		return this.book;
	}
	
	public String updateBook() {
		
		//1.将请求参数封装为Book对象
		
		//2.使用Dao更新数据
		dao.updateBook(book);
		
		return "success";
	}
	
	public String editUI() {
		
		//1.从Dao中根据bookId将用于回显的book对象取出
		Book book = dao.getBook(this.book.getBookId());
		
		//2.将book对象压入栈顶，便于回显
		ValueStack valueStack = ActionContext.getContext().getValueStack();
		valueStack.push(book);
		
		return "success";
	}
	
	public String removeBook() {
		
		dao.removeBook(this.book.getBookId());
		
		return "success";
	}
	
	public String saveBook() {
		
		//1.将请求参数值封装为Book对象
		
		//2.通过Dao将Book对象保存到“数据库”中
		dao.saveBook(book);
		
		return "success";
	}
	
	public String showBookList() {
		
		List<Book> bookList = dao.getBookList();
		this.requestMap.put("bookList", bookList);
		
		return "success";
	}
	
	//===================get、set方法区====================

	@Override
	public void setRequest(Map<String, Object> request) {
		this.requestMap = request;
	}

}
