package com.atguigu.spring.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atguigu.spring.PersonAction;

public class AutoWiredMain {
	
	public static void main(String[] args) {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("autowired.xml");
		PersonAction personAction = (PersonAction) ctx.getBean("personAction");
		
		System.out.println(personAction);
		personAction.execute();
	}
	
}
