package com.atguigu.spring.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

	@Autowired
	private OrderDao orderDao;
	
	@Transactional(noRollbackFor={ArithmeticException.class})
	public void save(Order order){
		this.orderDao.save(order);
		
//		int i = 10 / 0;
//		
//		Order order2 = new Order();
//		order2.setOrderNumber("20000001");
//		orderDao.save(order2);
	}
}
