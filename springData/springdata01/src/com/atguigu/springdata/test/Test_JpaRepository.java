package com.atguigu.springdata.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atguigu.springdata.dao.PersonRepository;
import com.atguigu.springdata.entities.Person;

public class Test_JpaRepository
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
		Person person = new Person();
		person.setAge(117);
		person.setBirth(new Date());
		person.setEmail("z3@163.com");
		person.setName("z3");
		
		person.setId(28);
		
		personRepository.saveAndFlush(person);
	}
}
