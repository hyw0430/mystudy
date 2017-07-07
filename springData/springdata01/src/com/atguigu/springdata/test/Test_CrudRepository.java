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

public class Test_CrudRepository
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
		List<Person> list = new ArrayList<Person>();
		
		list.add(new Person("li2", "li2@163.com", 23, new Date()));
		list.add(new Person("li3", "li3@163.com", 24, new Date()));
		list.add(new Person("li4", "li4@163.com", 25, new Date()));
		
		personRepository.save(list);
	}

	@Test
	public void test2()
	{
		List<Person> list = new ArrayList<Person>();
		Set<Person> sets = new HashSet<Person>();
		String tempName;
		for (int i = 'a'; i <='z'; i++)
		{
			tempName = ""+(char)i+(char)i;
			sets.add(new Person(tempName, tempName+"@sohu.com",new java.util.Random().nextInt(99),new Date()));
		}
		list.addAll(sets);
		
		personRepository.save(list);
	}
}
