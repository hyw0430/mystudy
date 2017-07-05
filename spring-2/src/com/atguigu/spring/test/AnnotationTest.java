package com.atguigu.spring.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atguigu.spring.annotation.CustomerAction;
import com.atguigu.spring.annotation.OrderAction;
import com.atguigu.spring.annotation.PersonAction;

public class AnnotationTest {
	
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("annotation.xml");
		PersonAction personAction = (PersonAction) ctx.getBean("personAction");
		//System.out.println(personAction);
		//personAction.execute();
		
		CustomerAction customerAction = (CustomerAction) ctx.getBean("customerAction");
		customerAction.execute();
		
		OrderAction orderAction = (OrderAction) ctx.getBean("orderAction");
		orderAction.execute();
	}
	
}
