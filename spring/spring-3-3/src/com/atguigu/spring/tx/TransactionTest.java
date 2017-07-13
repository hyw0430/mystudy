package com.atguigu.spring.tx;

import java.util.Arrays;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TransactionTest {

	private ApplicationContext ctx = null;
	private BookShopDao bookShopDao = null;
	private BookShopService bookShopService = null;
	private Cashier cashier;
	
	{
		ctx = new ClassPathXmlApplicationContext("transaction.xml");
		bookShopDao = ctx.getBean(BookShopDao.class);
		bookShopService = ctx.getBean(BookShopService.class);
		cashier = ctx.getBean(Cashier.class);
	}
	
	@Test
	public void testCashier(){
		cashier.checkout("Tom", Arrays.asList("1001","1002"));
	}
	
	@Test
	public void testBookShopService(){
		bookShopService.purchase("1002", "Tom");
	}
	
	@Test
	public void testBookShopDao(){
		int price = bookShopDao.findPriceByIsbn("1001");
		System.out.println(price);
		
		bookShopDao.updateBookStock("1001");
		
		bookShopDao.updateAccountBalance("Tom", 100);
	}
	
}
