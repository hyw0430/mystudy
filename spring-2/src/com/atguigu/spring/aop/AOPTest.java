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
	public void testAOP() {
		ArithmeticCalculator arithmeticCalculator = (ArithmeticCalculator) ctx.getBean("arithmeticCalculator");
		
		int result = arithmeticCalculator.add(11, 22);
		System.out.println(result);
		
		result = arithmeticCalculator.mul(11, 22);
		System.out.println(result);
	}
	

}
