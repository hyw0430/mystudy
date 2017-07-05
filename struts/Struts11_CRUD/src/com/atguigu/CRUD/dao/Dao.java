package com.atguigu.CRUD.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.atguigu.CRUD.bean.Book;

public class Dao {
	
	private static Map<Integer, Book> bookMap = null;
	
	static {
		bookMap = new LinkedHashMap<>();
		bookMap.put(1, new Book(1, "bookName01", "author01", 10.0));
		bookMap.put(2, new Book(2, "bookName02", "author02", 20.0));
		bookMap.put(3, new Book(3, "bookName03", "author03", 30.0));
		bookMap.put(4, new Book(4, "bookName04", "author04", 40.0));
		bookMap.put(5, new Book(5, "bookName05", "author05", 50.0));
		bookMap.put(6, new Book(6, "bookName06", "author06", 60.0));
		bookMap.put(7, new Book(7, "bookName07", "author07", 70.0));
	}
	
	public void updateBook(Book book) {
		bookMap.put(book.getBookId(), book);
	}
	
	public Book getBook(Integer bookId) {
		return bookMap.get(bookId);
	}
	
	public void removeBook(Integer bookId) {
		bookMap.remove(bookId);
	}
	
	public void saveBook(Book book) {
		book.setBookId((int)(System.currentTimeMillis()/100));
		bookMap.put(book.getBookId(), book);
	}
	
	public List<Book> getBookList() {
		return new ArrayList<>(bookMap.values());
	}

}
