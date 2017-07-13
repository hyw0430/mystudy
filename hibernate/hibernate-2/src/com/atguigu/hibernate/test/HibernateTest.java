package com.atguigu.hibernate.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.jdbc.Work;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.atguigu.hibernate.Customer;
import com.atguigu.hibernate.Order;

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
	
	//可以直接删除多的一端, 但不能直接删除一的一端. 
	//因为 1 的一端来维护关联关系. 
	@Test
	public void testManyToOneDelete(){
//		Order order = new Order();
//		order.setId(1);
//		session.delete(order);
		
		Customer customer = new Customer();
		customer.setId(1);
		session.delete(customer);
	}
	
	//是否对其关联对象执行 UPDATE 语句, 取决于其关联的对象是否在 Session 缓存中!
	
	//默认情况下, update 不会更新游离对象的关联属性的属性值. 
	//因为 update 方法只负责把 Order 由游离状态变为持久化状态. 而不会把其关联的对象也变为持久化对象.
	//若希望对关联对象进行 update, 就再调用一起 update 方法. 
	@Test
	public void testManyToOneUpdate3(){
		Order order = (Order) session.get(Order.class, 1);
		System.out.println(order.getCustomer().getLastName());
		
		session.evict(order);
		session.evict(order.getCustomer());
		
		order.getCustomer().setLastName("AA");
		
		session.update(order);
		session.update(order.getCustomer());
	}
	
	//从 Session 中移除 Order, 不会影响其 flush Customer。 
	@Test
	public void testManyToOneUpdate2(){
		Order order = (Order) session.get(Order.class, 1);
		System.out.println(order.getCustomer().getLastName());
		
		session.evict(order);
		order.getCustomer().setLastName("AAAA");
	}
	
	//在 flush 缓存时可以更新关联对象的属性.
	@Test
	public void testManyToOneUpdate1(){
		Order order = (Order) session.get(Order.class, 1);
		
		order.getCustomer().setLastName("AAAA");
	}
	
	/**
	 * 默认情况下, 对关联的对象使用懒加载的策略. 
	 */
	@Test
	public void testManyToOneGet(){
		Order order = (Order) session.get(Order.class, 1);
		System.out.println(order.getOrderNumber());
		
		System.out.println(order.getCustomer().getClass().getName());
		System.out.println(order.getCustomer().getLastName());
	}
	
	/**
	 * 若先 save 一的一端的对象, 则发送 3 条 INSERT.
	 * 若先 save 多的一端的对象, 则额外发送 2 条 UPDATE . 
	 * 所以保存时建议先 save 一的一端. 
	 */
	@Test
	public void testManyToOneSave(){
		Customer customer = new Customer();
		customer.setLastName("BB");
		
		Order order1 = new Order();
		order1.setOrderNumber("2001");
		
		Order order2 = new Order();
		order2.setOrderNumber("2002");
		
		//设定关联关系
		order1.setCustomer(customer);
		order2.setCustomer(customer);
		
		//执行 save
		session.save(customer);

		session.save(order1);
		session.save(order2);
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
	public void testBlob() throws IOException{
		Student student = new Student("AA", "aa@163.com");
		student.setBirth(new Date());
		
		InputStream stream = new FileInputStream("400.jpg");
		//Hibernate 提供了一个方法来创建 Blob 数据类型所对应的对象
		Blob picture = Hibernate.getLobCreator(session).createBlob(stream, stream.available());
		student.setPicture(picture);
		
		session.save(student);
	}
	
	@Test
	public void testDateTypeProperty(){
		Student student = new Student("CC", "cc@atguigu.com");
		student.setBirth(new Date());
		
		session.save(student);
	}
	
	@Test
	public void testPropertyFormula(){
		Student student = (Student) session.get(Student.class, 1);
		System.out.println(student.getInfo());
	}
	
	@Test
	public void testPropertyUpdate(){
		Student student = (Student) session.get(Student.class, 1);
		student.setEmail("bb@163.com");
	}
	
	@Test
	public void testPropertyUnique(){
		Student student = new Student("BB", "bb@163.com");
		session.save(student);
	}
	
	//saveOrUpdate: 可以根据对象的状态来进行 save or update
	@Test
	public void testSaveOrUpdate(){
		Student student = new Student("AA", "aa@atguigu.com");
		student.setId(9);
		
		session.saveOrUpdate(student);
	}
	
	//delete 方法: 删除游离或持久化对象, 有 id 即可
	@Test
	public void testDelete(){
		Student student = new Student();
		student.setId(8);
		
		session.delete(student);
	}
	
	//update 方法: 可以把游离对象转为持久化对象. 并完成 update 操作. 
	@Test
	public void testUpdate(){
		Student student = new Student(8, "DD", "ee@atguigu.com");
		session.update(student);
		
		System.out.println("-------------------------------------------");
	}
	
	/**
	 * 1. 若 load 方法加载的对象在数据表中不存在, 他也会返回一个代理对象. 在使用对象属性时会抛出 
	 * org.hibernate.ObjectNotFoundException 异常.
	 * 2. 若调用 load 方法返回值的 getId 方法, 则正常运行
	 * 3. 若在初始化代理对象属性之前, 已经关闭了 Session, 则会抛出 
	 * org.hibernate.LazyInitializationException: could not initialize proxy - no Session 异常. 
	 */
	@Test
	public void testLoad(){
		Student student = (Student) session.load(Student.class, 8);
		System.out.println(student.getClass().getName());
		
		session.close();
		
		System.out.println(student.getId());
		System.out.println(student.getEmail());
	}
	
	//Session 的缓存. 也称之为 hibernate 的一级缓存. 
	//可以来缓存 get 或 load 方法查询的对象. 
	@Test
	public void testGetAndLoad(){
		//get 直接获取对象. 
		Student student = (Student) session.get(Student.class, 8);
		System.out.println("1. --------------------------------------");
		System.out.println(student.getLastName());
		
		session.clear();
		System.out.println("\n");
		
		//load 支持懒加载(延迟加载.)
		student = (Student) session.load(Student.class, 8);
		System.out.println("2. --------------------------------------");
		System.out.println(student.getLastName());
	}
	
	@Test
	public void testSave(){
		//在 save 之前, new 的对象并没有为 id 赋值, 所以它是一个临时对象. 
		//临时对象: 没有 id! 或者 id 为 null. 
		Student student = new Student("BB", "bb@atguigu.com");
		
		//执行 save 之后会变为持久化对象:
		//1. 和 Session 关联. 
		//2. 因为和 Session 关联, 所以肯定有 id. 
		session.save(student);
		System.out.println("1. -----------------------------------");
		
		System.out.println(student.getId());
		student.setLastName("CC");
		
		//持久化对象, 不能修改 id!
		//student.setId(100);
		
		//session.flush();
		System.out.println("2. -----------------------------------");
	}
	
	@Test
	public void testC3p0() {
		session.doWork(new Work() {
			@Override
			public void execute(Connection arg0) throws SQLException {
				System.out.println(arg0);
			}
		});
	}

}
