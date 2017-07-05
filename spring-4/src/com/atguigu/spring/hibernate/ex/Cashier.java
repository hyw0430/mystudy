package com.atguigu.spring.hibernate.ex;

import java.util.List;

public interface Cashier {

	void checkout(String username, List<String> isbns);
	
}
