package com.atguigu.springdata.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import com.atguigu.springdata.dao.PersonRepository;
import com.atguigu.springdata.entities.Person;

public class Test_PagingAndSortingRepository
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
		Order e = new Order(Direction.ASC,"id");
		
		List<Order> orders = new ArrayList<Order>();
		orders.add(e);
		
		Sort sort = new Sort(orders);
		Pageable pageable = new PageRequest(1,10,sort);
		
		Page<Person> pages = personRepository.findAll(pageable);
		
		System.out.println("pages.getTotalElements():"+pages.getTotalElements());
		System.out.println("pages.getTotalPages():"+pages.getTotalPages());
		System.out.println("pages.getSize():"+pages.getSize());
		System.out.println("pages.getNumberOfElements():"+pages.getNumberOfElements());
		System.out.println("pages.getContent():"+pages.getContent());
	}

}
