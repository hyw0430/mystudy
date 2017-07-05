package com.atguigu.spring.tx.xml;

import java.util.Arrays;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atguigu.spring.tx.xml.service.BookShopService;
import com.atguigu.spring.tx.xml.service.Cashier;

public class TransactionTest {

	private ApplicationContext ctx = null;
	private BookShopDao bookShopDao = null;
	private BookShopService bookShopService = null;
	private Cashier cashier;
	
	{
		ctx = new ClassPathXmlApplicationContext("transaction2.xml");
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
	
}
