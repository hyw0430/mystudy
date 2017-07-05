package com.atguigu.CRUD.action;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.atguigu.CRUD.bean.Book;
import com.atguigu.CRUD.dao.Dao;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
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
 *问题二：执行editUI()方法后，栈顶位置上出现了两个Book对象，浪费内存
 *问题分析：
 *	1.两个Book对象的来源
 *		①栈顶有值的Book对象：我们在editUI()方法中手动压入的
 *		②栈顶下面没有值的Book对象：我们通过ModelDriven拦截器压入的
 *解决方案
 *	1.在手动压入之前，先将空的对象弹出
 *问题三：在eidtUI()方法中，用到了值栈对象，而值栈对象是属于Struts2比较核心和底层的代码，耦合较为严重。
 *问题分析：希望能够借助于JDK提供的代码解决这个问题，将ModelDriven压入的Book对象利用上
 *解决方案一：用dao中取出的Book对象给this.book赋值——这个方法行不通
 *解决方案二：用dao中取出的Book对象的各个属性依次给this.book赋值，但是太繁琐
 *问题四：各个Action方法对模型对象[Book]的需求不同，有的需要空对象，有的需要从Dao中取出的对象
 *解决思路：为每一个Action方法准备合适的模型对象。所以这里的“准备”工作需要在getModel()方法执行之前来做。
 *	而这个准备工作可以交给prepare拦截器
 *解决方案：
 *	0.让当BookAction类实现Preparable接口
 *	1.为有需要的Action方法声明“prepareXxx()”方法
 *	2.方法命名的要求：prepare+目标方法首字母大写
 *	3.在prepareXxx()方法中为this.book赋值，需要空对象的就赋值空对象，需要Dao对象的就赋值Dao对象
 *Prepare拦截器原理
 *	1.在ModelDriven拦截器执行之前执行
 *	2.获取目标Action对象，检查其是否实现了Preparable接口
 *	3.如果实现了Preparable接口，则调用prepareXxx()前缀方法
 *问题五：在prepareEditUI()方法中获取不到bookId请求参数
 *解决方案
 *	1.声明bookId成员变量，提供setXxx()方法
 *	2.借助params拦截器给bookId赋值
 */
public class CopyOfBookAction05 implements RequestAware,ModelDriven<Book>,Preparable{
	
	private Dao dao = new Dao();
	private Map<String, Object> requestMap;
	
	private Book book;

	@Override
	public Book getModel() {
		return this.book;
	}
	
	public void prepareUpdateBook() {
		this.book = new Book();
	}
	
	public String updateBook() {
		
		//1.将请求参数封装为Book对象
		
		//2.使用Dao更新数据
		dao.updateBook(book);
		
		return "success";
	}
	
	public void prepareEditUI(){
		//隐患
		this.book = dao.getBook(this.book.getBookId());
	}
	
	public String editUI() {
		
		//并没有让栈顶的Book对象指向这个有值的daoBook
		/*Book daoBook = dao.getBook(this.book.getBookId());
		
		this.book = daoBook;*/
		
		Book daoBook = dao.getBook(this.book.getBookId());
		
		this.book.setBookName(daoBook.getBookName());
		this.book.setAuthor(daoBook.getAuthor());
		this.book.setPrice(daoBook.getPrice());
		
		return "success";
	}
	
	public String removeBook() {
		
		dao.removeBook(this.book.getBookId());
		
		return "success";
	}
	
	public void prepareSaveBook() {
		this.book = new Book();
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

	@Override
	public void prepare() throws Exception {
		System.out.println("prepare()...");
	}

}
