package com.atguigu.spring.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("bookShopService")
public class BookShopServiceImpl implements BookShopService {

	@Autowired
	private BookShopDao bookShopDao;
	
//	@Transactional(isolation=Isolation.READ_COMMITTED,
//			propagation=Propagation.REQUIRES_NEW,
//			readOnly=false,
//			timeout=5)
	@Transactional
	@Override
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
