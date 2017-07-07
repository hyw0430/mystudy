package com.atguigu.springdata.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atguigu.springdata.dao.PersonRepository;
import com.atguigu.springdata.entities.Person;
import com.atguigu.springdata.service.PersonService;

public class Test_QueryAnnotation
{
	
	private ApplicationContext ctx = null;
	private PersonRepository personRepository = null;
	private PersonService personService = null;
	
	@Before
	public void init()
	{
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		personRepository = ctx.getBean(PersonRepository.class);
		personService = ctx.getBean(PersonService.class);
		
	}
	
	@Test
	public void test()
	{
		List<Person> list = personRepository.getByNameAndEmail("rr","rr@sohu.com");
		System.out.println(list.size());
	}
	@Test
	public void test2()
	{
		List<String> list = personRepository.getByNameAndEmail2("rr","rr@sohu.com");
		System.out.println(list.size());
	}	
	@Test
	public void test3()
	{
		//int retValue = personRepository.updateEmail("abc@163.com",1); error
		int retValue = personService.updateEmail("abc@163.com",1);
		System.out.println(retValue);
	}
	@Test
	public void test4()
	{
		personRepository.dealMethod();
	}	
}
