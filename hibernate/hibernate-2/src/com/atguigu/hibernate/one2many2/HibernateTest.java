package com.atguigu.hibernate.one2many2;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
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
	
	//在先获取 1 的一端, 在获取多的一端的数据时, 可以选择对多的一端的数据进行 ORDER BY
	//order-by 属性中添加的是列的名字, 可以使用 ASC 或 DESC 关键字. 
	@Test
	public void testOrderBy(){
		Customer customer = (Customer) session.get(Customer.class, 3);
		System.out.println(customer.getOrders().size());
	}
	
	//若 1 的一端放弃维护关联关系. 则再有外键行的情况下, 不能直接删除 1 的一端的数据. 
	//若 1 的一端还维护着关联关系, 则可以通过设置 set 的 cascade 属性, 来修改删除一的一端时多的一端的处理方式
	//cascade 了解. 
	@Test
	public void testDelete(){
		Customer customer = (Customer) session.get(Customer.class, 1);
		session.delete(customer);
	}

	/**
	 * 双向关联关系. 
	 * 若先保存多的一端, 后保存一的一端, 则会多出 4 条 UPDATE 语句. 
	 * 若先保存一的一端, 后保存多的一端, 则会多出 2 条 UPDATE 语句. 
	 * 
	 * 如何让 hibernate 不生成额外的 UPDATE 语句呢? 一的一端不维护关联关系! 多的维护关联关系,可以改善性能!
	 * 可以通过设置 set 节点的 inverse 属性为 true, 使 一 的一端放弃维护关联关系.
	 * 
	 * 尽量使用 n-1, 尽可能少的使用 1-n
	 */
	@Test
	public void testSave(){
		Customer customer = new Customer();
		customer.setLastName("CC");
		
		Order order1 = new Order();
		order1.setOrderNumber("3001");
		
		Order order2 = new Order();
		order2.setOrderNumber("3002");
		
		//设置单向 一对多 的关联关系
		customer.getOrders().add(order1);
		customer.getOrders().add(order2);
		
		order1.setCustomer(customer);
		order2.setCustomer(customer);
		
		//执行保存
		session.save(customer);

		session.save(order1);
		session.save(order2);
	}
}
