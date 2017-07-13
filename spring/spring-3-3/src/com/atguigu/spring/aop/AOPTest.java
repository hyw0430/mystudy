package com.atguigu.spring.aop;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AOPTest {
	
	private ApplicationContext ctx = null;
	
	{
		ctx = new ClassPathXmlApplicationContext("aop.xml");
	}
	
	@Test
	public void testBefore() {
		ArithmeticCalculator arithmeticCalculator = 
				(ArithmeticCalculator) ctx.getBean("arithmeticCalculator");
		System.out.println(arithmeticCalculator.getClass().getName());
		
		int result = arithmeticCalculator.add(1, 2);
		System.out.println("result: " + result);
		
		result = arithmeticCalculator.div(10, 10);
		System.out.println("result: " + result);
	}

}
