package com.atguigu.spring.hibernate;

import java.util.Date;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringHibernateTest {
	
	private ApplicationContext ctx = null;
	private OrderService orderService;
	
	{
		ctx = new ClassPathXmlApplicationContext("hibernate.xml");
		orderService = ctx.getBean(OrderService.class);
	}
	
	@Test
	public void testOrderService() {
		Order order = new Order();
		order.setOrderDate(new Date());
		order.setOrderNumber("11001");
		Customer customer = new Customer();
		customer.setId(2);
		order.setCustomer(customer);
		
		orderService.save(order);
	}

}
