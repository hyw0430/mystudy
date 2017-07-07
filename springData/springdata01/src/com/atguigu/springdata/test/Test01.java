package com.atguigu.springdata.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atguigu.springdata.dao.PersonRepository;
import com.atguigu.springdata.entities.Person;

public class Test01
{
	
	private ApplicationContext ctx = null;
	private PersonRepository personRepository = null;
	
	@Before
	public void init()
	{
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		personRepository = ctx.getBean(PersonRepository.class);
		
	}
	
	@Test
	public void test()
	{
//		Person person = personRepository.getByName("li4");
//		System.out.println(person.toString());
		Person person = personRepository.readByName("li4");
		System.out.println(person.toString());
		
		//System.out.println(personRepository.getClass().getName());
	}

}
