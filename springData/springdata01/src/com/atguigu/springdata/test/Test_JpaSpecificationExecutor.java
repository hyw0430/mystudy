package com.atguigu.springdata.test;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
import org.springframework.data.jpa.domain.Specification;

import com.atguigu.springdata.dao.PersonRepository;
import com.atguigu.springdata.entities.Person;

public class Test_JpaSpecificationExecutor
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
		Pageable pageable = new PageRequest(0,10,sort);
		
		
		Specification specification = new Specification<Person>()
		{
			 /**
			 * @param root  				导航root根对象，获得对象的属性（属性的属性的）path路径依赖
			 * @param criteriaQuery 		几乎不用
			 * @param criteriaBuilder 		CriteriaBuilder的查询工厂类，类似where后面的各种条件组合。
			 * @return Predicate 			Criteria的一个查询条件；
			 */			
			@Override
			public Predicate toPredicate(Root<Person> root,
					CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder)
			{
				Predicate p1 = criteriaBuilder.gt((Expression)root.get("id"),5);
				Predicate p2 = criteriaBuilder.equal((Expression)root.get("name"),"ee");
				
				return criteriaBuilder.and(p1,p2);
			}
		};
		
		Page<Person> pages = personRepository.findAll(specification, pageable);
		
		System.out.println("pages.getTotalElements():"+pages.getTotalElements());
		System.out.println("pages.getTotalPages():"+pages.getTotalPages());
		System.out.println("pages.getSize():"+pages.getSize());
		System.out.println("pages.getNumberOfElements():"+pages.getNumberOfElements());
		System.out.println("pages.getContent():"+pages.getContent());
	}

}
