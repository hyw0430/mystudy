package com.atguigu.spring.tx.xml.service;

import java.util.List;

public interface Cashier {

	void checkout(String username, List<String> isbns);
	
}
