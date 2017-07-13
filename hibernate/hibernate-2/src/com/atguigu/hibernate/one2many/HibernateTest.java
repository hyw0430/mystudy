package com.atguigu.hibernate.one2many;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.jboss.logging.Cause;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HibernateTest {
	
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;
	
	@Before
	public void init(){
		Configuration configuration = new Configuration().configure();
		ServiceRegistry serviceRegistry = 
				new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
	}
	
	@After
	public void destroy(){
		//默认情况下: 在提交事务时做两件事情:
		//1. flush 缓存. 也可以在提交事务之前手工调用. 
		//2. 提交事务
		transaction.commit();
		session.close();
		sessionFactory.close();
	}
	
	//默认情况下, 可以直接删除 1 的一端的对象.
	//默认行为是, 先把多的一端的外键置为 null
	@Test
	public void testDelete2(){
		Customer customer = (Customer) session.get(Customer.class, 2);
		session.delete(customer);
	}

	//可以直接删除 多的一端的对象
	@Test
	public void testDelete(){
		Customer customer = (Customer) session.get(Customer.class, 1);
		Order order = customer.getOrders().iterator().next();
		session.delete(order);
	}
	
	//可以级联更新集合中的元素的属性. 
	@Test
	public void testUpdate(){
		Customer customer = (Customer) session.get(Customer.class, 1);
		customer.getOrders().iterator().next().setOrderNumber("3001");
	}
	
	/**
	 * 默认情况下, Customer 关联的 orders 集合使用懒加载策略: 在查询 Customer 的时候, 并不会同时查询出其所关联的 Order.
	 */
	@Test
	public void testGet(){
		Customer customer = (Customer) session.get(Customer.class, 1);
		System.out.println(customer.getLastName());
		
		System.out.println(customer.getOrders().getClass().getName());
		System.out.println(customer.getOrders().size());
	}
	
	//若使用单向 1-n 的关联关系. 无论先保存哪一端, 都会生成 UPDATE 语句. 
	//因为 Order 不维护关联关系, 所以在插入 Order 数据时, Order 并不知道和哪些 Customer 关联. 所以插入 Order 时,
	//不会插入外键列的值. 这就只能等待 Order 和 Customer 都插入成功后, 由 hibernate 去更新外键列的值. 
	@Test
	public void testSave(){
		Customer customer = new Customer();
		customer.setLastName("BB");
		
		Order order1 = new Order();
		order1.setOrderNumber("2001");
		
		Order order2 = new Order();
		order2.setOrderNumber("2002");
		
		//设置单向 一对多 的关联关系
		customer.getOrders().add(order1);
		customer.getOrders().add(order2);
		
		//执行保存
		session.save(customer);

		session.save(order1);
		session.save(order2);
	}
}
