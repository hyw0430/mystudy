package com.atguigu.springdata.test;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atguigu.springdata.dao.PersonRepository;
import com.atguigu.springdata.entities.Person;

public class Test_QueryProcess
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
		List<Person> list = personRepository.getByAddress_CityLike("%bj%");
		System.out.println(list.size());
	}

}
