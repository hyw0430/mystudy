package com.atguigu.spring.tx.xml.service;

public interface BookShopService {

	//查询书的单价, 库存 - 1, 更新余额. 
	void purchase(String isbn, String username);
	
}
