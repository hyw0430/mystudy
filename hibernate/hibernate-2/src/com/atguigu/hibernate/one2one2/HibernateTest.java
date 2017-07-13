package com.atguigu.hibernate.one2one2;

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
	
	@Test
	public void testOne2OneGet2(){
		Manager manager = (Manager) session.get(Manager.class, 1);
		System.out.println(manager.getLastName());
	}
	
	@Test
	public void testOne2OneGet(){
		Department department = (Department) session.get(Department.class, 3);
		System.out.println(department.getDeptName());
		
		System.out.println(department.getManager().getClass().getName());
		
		System.out.println("------------------------------");
		System.out.println(department.getManager().getLastName());
	}
	
	/**
	 * 因为使用外键列来生成当前记录的主键, 所以必须设置外键列属性所在的关联关系!
	 */
	@Test
	public void testOne2OneSave(){
		Manager manager = new Manager();
		manager.setLastName("BB");
		
		Department department = new Department();
		department.setDeptName("DEV");
		
		//设置关联关系
		department.setManager(manager);
		manager.setDepartment(department);
		
		//执行保存操作
		session.save(department);
		session.save(manager);
	}
}
