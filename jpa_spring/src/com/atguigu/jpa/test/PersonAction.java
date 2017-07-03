package com.atguigu.jpa.test;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atguigu.jpa.entities.Person;
import com.atguigu.jpa.service.PersonService;

public class PersonAction
{
	
	private ApplicationContext ctx = null;
	private PersonService personService = null;
	
	@Before
	public void init()
	{
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		personService = ctx.getBean(PersonService.class);
	}
	
	@Test
	public void test()throws IOException
	{
		Person person = new Person();
		person.setName("z77");
		
		personService.add(person);
	}
}
