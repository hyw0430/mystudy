package com.atguigu.CRUD.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.atguigu.CRUD.bean.Book;
import com.atguigu.CRUD.dao.Dao;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class BookAction implements RequestAware,ModelDriven<Book>,Preparable{
	
	private Dao dao = new Dao();
	private Map<String, Object> requestMap;
	
	private Book book;
	private Integer bookId;
	
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	@Override
	public Book getModel() {
		return this.book;
	}
	
	public void prepareUpdateBook() {
		this.book = new Book();
	}
	
	public String updateBook() {
		dao.updateBook(book);
		return "success";
	}
	
	public void prepareEditUI(){
		this.book = dao.getBook(bookId);
	}
	
	public String editUI() {
		
		return "success";
	}
	
	public String removeBook() {
		dao.removeBook(bookId);
		return "success";
	}
	
	public void prepareSaveBook() {
		this.book = new Book();
	}
	
	public String saveBook() {
		dao.saveBook(book);
		
		return "success";
	}
	
	public String showBookList() {
		
		List<Book> bookList = dao.getBookList();
		this.requestMap.put("bookList", bookList);
		
		return "success";
	}
	
	@Override
	public void setRequest(Map<String, Object> request) {
		this.requestMap = request;
	}

	@Override
	public void prepare() throws Exception {}

}
