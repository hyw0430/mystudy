package com.atguigu.hibernate.one2one;

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
	
	/**
	 * 基于外键映射的双线 1-1 的关联关系.
	 * 1. 若先获取没有外键列的 bean. 则语句是一个左外连接! 而不是单独的获取当前的 bean 的一个 SELECT.
	 * 原因: 因为外键列不在当前的数据表中, 所以 hibernate 不知道为关联对象赋值为 null 还是生成代理. 所以必须使用左外连接查询出来!
	 * 2. 左外连接的条件需要使用外键来连接, 而不能是主键. 所以需要设置  one-to-one 节点的 property-ref 属性, 指定使用对方的
	 * 哪个属性所对应的列来连接. 
	 */
	@Test
	public void testOne2OneGet2(){
		Manager manager = (Manager) session.get(Manager.class, 1);
		System.out.println(manager.getLastName());
		
//		System.out.println("-------------------------------");
//		System.out.println(manager.getDepartment().getDeptName());
	}
	
	/**
	 * 基于外键映射的单向 1-1 的关联关系
	 * 1. 其实就是添加了唯一约束的多对一关联关系.
	 */
	@Test
	public void testOne2OneGet(){
		Department department = (Department) session.get(Department.class, 3);
		System.out.println(department.getDeptName());
		
		System.out.println(department.getManager().getClass().getName());
		
//		System.out.println("------------------------------");
//		System.out.println(department.getManager().getLastName());
	}
	
	@Test
	public void testOne2OneSave(){
		Manager manager = new Manager();
		manager.setLastName("BB");
		
		Department department = new Department();
		department.setDeptName("DEV");
		
		//设置关联关系
		department.setManager(manager);
		
		//执行保存操作
		session.save(manager);
		session.save(department);
	}
}
