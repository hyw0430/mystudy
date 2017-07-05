package com.atguigu.spring.tx;

public interface BookShopDao {

	int findPriceByIsbn(String isbn);
	
	//库存 - 1
	void updateBookStock(String isbn);
	
	//根据用户名, 使用户的余额 - price
	void updateAccountBalance(String username, int price);
}
