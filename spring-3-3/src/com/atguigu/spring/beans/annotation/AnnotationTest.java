package com.atguigu.spring.beans.annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationTest {
	
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("annotation.xml");
		CustomerService customerService = (CustomerService) ctx.getBean("customerService");
		customerService.save();
	}
	
}
