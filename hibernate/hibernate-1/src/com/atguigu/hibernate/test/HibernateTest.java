package com.atguigu.hibernate.test;

import java.sql.Connection;
import java.sql.SQLException;

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

import com.atguigu.hibernate.helloworld.News;

public class HibernateTest {

	private SessionFactory sessionFactory = null;
	private Session session = null;
	private Transaction tx = null;
	
	@Before
	public void init(){
		//创建 SessionFactory
		Configuration configuration = new Configuration().configure();
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		
		//获取 Session
		session = sessionFactory.openSession();
		
		//开启事务
		tx = session.beginTransaction();
	}
	
	@After
	public void destroy(){
		//提交事务
		tx.commit();
		
		//关闭 Session
		session.close();
		
		//关闭 SessionFactory
		sessionFactory.close();
	}
	
	@Test
	public void testUnsavedValue(){
		News news = new News();
		news.setId(10000);
		news.setAuthor("atguigu");
		news.setTitle("2000");
		
		session.saveOrUpdate(news);
		System.out.println(news.getId());
		System.out.println("----------------------");
	}
	
	@Test
	public void testSelectBeforeUpdate(){
		News news = new News();
		news.setId(1);
		news.setAuthor("atguigu");
		news.setTitle("2000");
		
		session.update(news);
	}
	
	@Test
	public void testDynamicUpdate(){
		News news = (News) session.get(News.class, 1);
		news.setTitle("2001");
	}
	
	//在 hibernate 中调用原生的 JDBC API
	@Test
	public void testDoWork(){
		session.doWork(new Work() {
			@Override
			public void execute(Connection connection) throws SQLException {
				System.out.println(connection);
				
				//调用原生的 JDBC API
			}
		});
	}
	
	//可以把一个 持久化对象 或 游离对象 删除数据表中对应的记录
	//删除有 id 的!
	@Test
	public void testDelete(){
		News news = new News();
		news.setId(5);
		
		session.delete(news);
	}
	
	/**
	 * 既可以进行 save 又可以进行 update.
	 * 若 id 为 null, 则进行 save 操作. 若 id 不为 null, 则进行 update 操作
	 */
	@Test
	public void testSaveOrUpdate(){
		News news = new News();
		news.setAuthor("atguiguv5");
		news.setId(1);
		news.setTitle("1998");
		
		session.saveOrUpdate(news);
	}
	
	/**
	 * 才创建的一个对象. 若设置了 id, 且和数据表中的一条记录对应, 则其实也是一个 游离对象. 因为有 id, 且数据表中有对应的记录. 
	 * 可以通过调用 update 方法来修改游离对象所对应的记录, 该方法可以把一个游离对象变为一个持久化对象. 
	 * 
	 * 而对于持久化对象, 若修改属性, 是不需要调用 update 方法. 
	 * 
	 * 1. 默认情况下, 只要调用 update 方法就会发送 UPDATE 语句, 而无论数据表中的记录和游离对象的属性是否一致.
	 * 2. 若数据表中没有 id 对应的记录, 则会抛出 StaleStateException 异常. 
	 */
	@Test
	public void testUpdate(){
		News news = new News();
		news.setAuthor("atguiguv5");
		news.setId(11);
		news.setTitle("1998");
		
		session.update(news);
	}
	
	//了解: 把某一个对象从 Session 缓存中移除. 即该对象不再和 Session 关联.
	//也就是说把对象由 持久化状态 变为 游离状态.
	//对于游离对象而言, 数据表中可能还有其对应的记录呢 !
	@Test
	public void testEvict(){
		News news = (News) session.get(News.class, 1);
		session.evict(news);
		
		System.out.println(news);
		news.setAuthor("atguiguv5");
	}
	
	//org.hibernate.LazyInitializationException: could not initialize proxy - no Session
	//懒加载初始化异常: 在有懒加载情况下可能会发生该异常. 具体在先返回了一个代理对象, 然后再初始化代理对象之前, 若关闭了 Session
	//则就会发生该异常. 
	@Test
	public void testLazyInitializationException(){
		News news = (News) session.load(News.class, 2);
		System.out.println("--------------------------------");
		System.out.println(news.getClass().getName());
		
		session.close();
		
		System.out.println(news.getAuthor());
	}
	
	//根据 id 从数据表中获取对应的记录
	//get: 发送 get 方法后, 立即执行 SELECT 语句. 若数据表中没有对应的记录, 则返回 null
	//load: 支持懒加载! 先返回一个代理对象, 在使用持久化对象具体属性时, 才发送 SELECT 语句. 若数据表中没有对应的记录, 则抛出异常:
	//org.hibernate.ObjectNotFoundException
	@Test
	public void testGetAndLoad(){
		News news = (News) session.get(News.class, 100);
		System.out.println("--------------------------------");
		System.out.println(news == null ? news : news.getAuthor());
		
		news = (News) session.load(News.class, 200);
		System.out.println("--------------------------------");
		System.out.println(news.getClass().getName());
		System.out.println(news == null ? news : news.getAuthor());
	}
	
	/**
	 * 和 save 方法的区别:
	 * 对于 save 方法, 若传入的对象有 id, 则其会忽略 id, 直接执行 insert. 
	 * 对于 persist 方法, 若传入的对象有 id, 其会抛出异常.  即 persist 方法认为有 id 的对象不是一个临时对象. 
	 */
	@Test
	public void testPersist(){
		News news = new News();
		news.setAuthor("AA");
		news.setTitle("2000");
		news.setId(100000);
		
		System.out.println(news.getId());
		
		session.persist(news);
		System.out.println(news.getId());
		System.out.println("---------------------------");
	}
	
	//save 方法: 把一个对象保持到数据表中.
	/**
	 * 1. 把临时对象变为持久化对象: 即执行了 save 方法后, 对象一定是有 ID 的!
	 * 2. 尝试发送一条 INSERT 语句.
	 * 3. 对于 MySQL 数据库而言, 若使用了 native 的主键生成方式(数据库自增), 则只要在发送 INSERT 语句之后才能得到 ID!
	 * 若主键使用了其他的方式, 则 save 方法仅仅得到 ID, 而在提交事务(或清理缓存)时才发送 INSERT
	 * 4. 变为持久化对象之后, Session 可以感知到其属性的变化, 可以根据属性的变化情况, 发送对应的 UPDATE 语句. 
	 * 5. 对于持久化对象, 不允许程序修改它的 ID
	 */
	@Test
	public void testSave(){
		News news = new News();
		news.setAuthor("Mike");
		news.setTitle("1998");
		news.setId(100000);
		System.out.println(news.getId());
		
		session.save(news);
		System.out.println(news.getId());
		
		news.setTitle("Java");
		news.setId(10000);
		
		System.out.println("---------------------------");
	}
	
	//了解: 刷新缓存. 
	/**
	 * 再发送一条 SELECT 语句来获取对应记录, 以使 Session 缓存中对象的状态和数据表保持一致. 
	 * 若希望在 MySQL 中演示出对应的效果, 则需要在 hibernate.cfg.xml 文件中设置事务的隔离级别为 读已提交
	 * 
	 * <property name="hibernate.connection.isolation">2</property>
	 */
	@Test
	public void testRefresh(){
		News news1 = (News) session.get(News.class, 1);
		System.out.println(news1);
		
		System.out.println("--------------------------------------------");
		session.refresh(news1);
		
		System.out.println(news1);
	}
	
	//清理缓存: 
	/**
	 * 默认情况下: 在调用 Transaction 的 commit 方法时:
	 * 1). 检查当初查询的对象的属性是否发生变化, 若变化了, 则发送 UPDATE 语句 -- 清理缓存
	 * 2). 提交事务. 
	 * 
	 * 可以手工调用 Session 的 flush 方法来清理缓存. 
	 * 1). 清理缓存: 检查当初查询的对象的属性是否发生变化, 若变化了, 则发送 UPDATE 语句
	 * 2). 清理缓存, 只发送 UPDATE 语句, 但不进行事务的提交
	 */
	@Test
	public void testFlush(){
		News news1 = (News) session.get(News.class, 1);
		news1.setTitle("ABCD");
		
		session.flush();
		
		System.out.println("testFlush");
	}
	
	//清空缓存: 把缓存置空
	@Test
	public void testClear(){
		News news1 = (News) session.get(News.class, 1);
		
		session.clear();
		
		News news2 = (News) session.get(News.class, 1);
		
		System.out.println(news1 == news2);
	}
	
	//测试 Session 缓存. 即 Hibernate 的一级缓存
	/**
	 * session 调用 get 方法的过程:
	 * 1. 查询缓存中是否有和 id 对应的对象. 若有, 则直接返回
	 * 2. 若没有, 则查询数据表. 并把查询的结果纳入到 Session 缓存中. 并返回该对象. 
	 * 
	 * 所以在一个 Session 的生命周期中多次调用 get 方法, 查询相同 id 的对象, 返回的实际上是一个对象. 
	 */
	@Test
	public void testSessionCache() {
		News news1 = (News) session.get(News.class, 1);
		News news2 = (News) session.get(News.class, 1);
		
		System.out.println(news1 == news2);
	}
	
	//调用 get 方法根据 id 从数据表中加载对应的记录
	@Test
	public void testGet(){
		//session.get(clazz, id): 
		//clazz: 实体类的类型对应的 Class 对象
		//id: id
		News news = (News) session.get(News.class, 1);
		System.out.println(news);
	}

}
