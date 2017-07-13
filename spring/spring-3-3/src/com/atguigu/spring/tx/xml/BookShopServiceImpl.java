package com.atguigu.spring.tx.xml;

import com.atguigu.spring.tx.xml.service.BookShopService;


public class BookShopServiceImpl implements BookShopService {

	private BookShopDao bookShopDao;
	
	public void setBookShopDao(BookShopDao bookShopDao) {
		this.bookShopDao = bookShopDao;
	}
	
	public void purchase(String isbn, String username) {
		int price = bookShopDao.findPriceByIsbn(isbn);
		
		//更新数的库存
		bookShopDao.updateBookStock(isbn);
		
//		try {
//			Thread.sleep(7000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		
		//更新余额
		bookShopDao.updateAccountBalance(username, price);
	}

}
