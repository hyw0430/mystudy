package com.atguigu.hibernate.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.jdbc.Work;
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
		transaction.commit();
		session.close();
		sessionFactory.close();
	}
	
	/**
	 * 批量处理:
	 * 1.删除 和 更新 可以使用 HQL 语句
	 * 2.所以通常针对添加而言.
	 * 
	 * 首选使用原生 JDBC 的方式. 
	 * 
	 * 问题:
	 * 1. 若直接调用 session.save 方法, 会导致一级缓存被撑爆! 程序崩溃. 
	 * 解决:
	 * save 一部分就 flush, 然后 clear 缓存. 
	 * 
	 * 2. 对于使用数据表主键自增方式的数据库, 此种方法无效. 因为每次执行 save 方法都会导致直接发送 INSERT .
	 * 若项目中有批量操作, 则推荐修改主键的生成方式.  
	 */
	@Test
	public void testHibernateBatchExecute(){
		for(int i = 0; i < 1000000; i++){
			Category category = new Category();
			category.setCategoryName("C-" + i);
			
			session.save(category);
			
			if((i + 1) % 50 == 0){
				session.flush();
				session.clear();
			}
		}
		
		//提交事务
		
		session.doWork(new Work() {
			@Override
			public void execute(Connection connection) throws SQLException {
				//
			}
		});
	}
	
	class OrderDao{
		
		private SessionFactory sessionFactory;
		
		public void setSessionFactory(SessionFactory sessionFactory) {
			this.sessionFactory = sessionFactory;
		}
		
		public Session getSession(){
			return this.sessionFactory.getCurrentSession();
//			return this.sessionFactory.openSession();
		}
		
		//保存一个新的订单
		void save(Order order){
			System.out.println("saveOrder: " + getSession().hashCode());
		}
		
		//删除一个订单
		void delete(Order order){
			System.out.println("deleteOrder: " + getSession().hashCode());
		}
		
	}
	
	/**
	 * 问题: 若每个 Dao 方法都调用 SessionFactory 的 openSession() 方法, 则每个 Dao 将得到不同的新的 Session 对象.
	 * 此时是没有办法完成事务操作的. 因为事务要求多个 Dao 方法使用同一个 Session(即同一个链接)
	 * 
	 * 可以调用 SessionFactory 的 getCurrentSession() 来获取和当前线程绑定的 Session!
	 * 首先需要在 hibernate.cfg.xml 中配置 
	 * hibernate.current_session_context_class 属性为 thread
	 * 
	 * 实际开发中, Hibernate 通常和 Spring 进行整合, 不需要配置上诉的属性, 而直接调用 
	 * SessionFactory 的 getCurrentSession() 来获取和当前线程绑定的 Session!
	 */
	@Test
	public void testGetCurrentSession(){
		OrderDao orderDao = new OrderDao();
		orderDao.setSessionFactory(sessionFactory);
		
		Session session = sessionFactory.getCurrentSession();
		System.out.println(session.hashCode());
		session.beginTransaction();
		
		orderDao.save(new Order());
		orderDao.delete(new Order());
		
		session.getTransaction().commit();
		session.close();
	}
	
	/**
	 * 了解. 及不常用.
	 * 仅是为了区分和 list 方法的区别. 
	 * 
	 * 查询过程:
	 * 1. iterator() 方法执行的SQL 语句中仅包含实体类对应的数据表的 ID 字段
	 * 2. 当遍历访问结果集时, 该方法先到 Session 缓存及二级缓存中查看是否存在特定 OID 的对象, 如果存在, 就直接返回该对象, 
	 * 如果不存在该对象就通过相应的 SQL Select 语句到数据库中加载特定的实体对象
	 */
	@Test
	public void testQueryIterator(){
		String hql = "SELECT o FROM Order o";
		Query query = session.createQuery(hql).setCacheable(true);
		Iterator<Order> orders = query.iterate();
		
		while(orders.hasNext()){
			System.out.println(orders.next().getOrderNumber());
		}
	}
	
	/**
	 * 具体参见 ehcache.xml 文件中的注释
	 */
	@Test
	public void testEhcacheConfFile() throws InterruptedException{
		String hql = "SELECT o FROM Order o";
		Query query = session.createQuery(hql).setCacheable(true);
		List<Order> orders = query.list();
		
		transaction.commit();
		session.close();
		
		Thread.sleep(8000);
		
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		
		Query query2 = session.createQuery(hql).setCacheable(true);
		List<Order> orders2 = query2.list();
	}
	
	/**
	 * 了解:
	 * 更新时间戳缓存. 
	 * 具体参见 PPT
	 */
	@Test
	public void testUpdateStampCache(){
		Category category1 = (Category) session.get(Category.class, 3);
		System.out.println(category1.getCategoryName());
		
		//利用 HQL 语句更新 Category 对象
		String hql = "UPDATE Category c SET c.categoryName = :cn WHERE c.id = :id";
		session.createQuery(hql).setParameter("cn", "ABABAB").setParameter("id", 3).executeUpdate();
		
		transaction.commit();
		session.close();
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		
		Category category2 = (Category) session.get(Category.class, 3);
		System.out.println(category2.getCategoryName());
	}
	
	/**
	 * 在进行 HQL 查询时, 查询结果可以被缓存吗 ?
	 * 1. 配置启用查询缓存
	 * 2. 调用 Query 的 setCacheable(true) 方法, 使 Query 对象是可被缓存的. 
	 * 3. 查询缓存也是可以跨越 Session 的. 
	 */
	@Test
	public void testQueryCache(){
		String hql = "SELECT c FROM Category c";
		Query query = session.createQuery(hql).setCacheable(true);
		List<Category> categories = query.list();
		
		transaction.commit();
		session.close();
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		
		Query query2 = session.createQuery(hql).setCacheable(true);
		categories = query2.list();
	}
	
	/**
	 * 额外配置:
	 * 1. 配置集合中的类的类级别的二级缓存
	 * 2. 配置集合的二级缓存
	 */
	@Test
	public void testCollectionCache(){
		Category category1 = (Category) session.get(Category.class, 3);
		System.out.println(category1.getItems().size());
		
		transaction.commit();
		session.close();
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		
		Category category2 = (Category) session.get(Category.class, 3);
		
		System.out.println(category1 == category2);
		System.out.println(category2.getItems().size());
	}
	
	/**
	 * Hibernate 二级缓存:
	 * 1. 相对于 一级缓存(Session 级别的) 而言. 
	 * 2. 二级缓存是 SessionFactory 级别的, 可以跨多个 Session. 
	 * 3. 需要单独配置, 是可插拔的. 
	 * 
	 * 配置 hibernate 的二级缓存
	 * 1. 加入二级缓存实现产品的 jar 包. hibernate-release-4.2.4.Final\lib\optional\ehcache\*.jar
	 * 2. 加入 ehcache 的配置文件. 加入到类路径下. hibernate-release-4.2.4.Final\project\etc\ehcache.xml
	 * 3. 在 hibernate.cfg.xml 文件配置使用二级缓存
	 * 3.1 配置启用二级缓存
	 * 3.2 配置二级缓存的使用产品
	 * 4. 配置对哪些类进行缓存. 以及对应的缓存策略. 
	 * 5. 在二级缓存中保存的是对象的散装数据. 所以缓存中取到的对象和源对象不是一个. 
	 */
	@Test
	public void testSecondLevelCache(){
		Category category1 = (Category) session.get(Category.class, 3);
		
		transaction.commit();
		session.close();
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		
		Category category2 = (Category) session.get(Category.class, 3);
		
		System.out.println(category1 == category2);
	}
	
	@Test
	public void testFirstLevelCache(){
		Category category1 = (Category) session.get(Category.class, 3);
		Category category2 = (Category) session.get(Category.class, 3);
		
		System.out.println(category1 == category2);
	}
	
	//多对多的分组: 需要内连接属性后, 才可以使用属性进行分组. 
	//需求: 查询每个 Category 中有多少个 Item. 
	//先些一个 SQL
	/**
	 *  SELECT c.id, COUNT(i.id)
	 *	FROM hibernate_items i
	 * 	INNER JOIN category_item ci
	 * 	ON i.id = ci.item_id
	 * 	INNER JOIN hibernate_categories c
	 * 	ON ci.category_id = c.id
	 *	GROUP BY c.id
	 * 
	 * 
	 */
	//再完成一个 HQL
	/**
	 * SELECT c.id, count(i.id)
	 * FROM Item i 
	 * INNER JOIN i.categories c
	 * GROUP BY c
	 */
	@Test
	public void testGroupBy3(){
		String hql = "SELECT c.id, count(i.id) "
				+ "FROM Item i "
				+ "INNER JOIN i.categories c "
				+ "GROUP BY c";
		List<Object[]> results = session.createQuery(hql).list();
		
		for(Object[] objs: results){
			System.out.println(objs[0] + ", " + objs[1]);
		}
	}
	
	//一对多 不需要进行分组. 
	@Test
	public void testGroup2(){
		
	}
	
	//多对一的 group by
	@Test
	public void testGroupBy(){
		String hql = "SELECT o.customer.lastName, count(o.id) "
				+ "FROM Order o "
				+ "GROUP BY o.customer ";
		List<Object[]> results = session.createQuery(hql).list();
		
		for(Object[] objs: results){
			System.out.println(objs[0] + ": " + objs[1]);
		}
	}
	
	@Test
	public void testHQL(){
		String hql = "UPDATE Category c SET c.categoryName = :cn WHERE c.id = :id";
		Query query = session.createQuery(hql).setParameter("cn", "AAAAAA").setParameter("id", 3);
		
		query.executeUpdate();
	}
	
	/**
	 * QBC: Query By Criteria 即使用 Criteria 进行查询. 
	 * 对于 QBC 查询, fetch 属性时有效的. 
	 */
	@Test
	public void testCriteria(){
		//1. 创建 Criteria 对象
		Criteria criteria = session.createCriteria(Category.class);
		
		//2. 创建查询条件
		Criterion criterion = Restrictions.between("id", 1, 3);
		
		//3. 添加查询条件
		criteria.add(criterion);
		
		//4. 查询.
		List<Category> categories = criteria.list();
		System.out.println(categories.size());
	}
	
	/**
	 * 1. 使用 HQL 查询会忽略 set 节点的 fetch 属性. 
	 * 2. 若只在 HQL 中使用左外(内)连接, 将会发送左外(内连接)连接的 SQL, 但其关联的属性不会被初始化. 
	 * 只有在 JOIN 后添加 FETCH 关键字, 才能初始化其关联的属性. 
	 * 3. FETCH 是 HQL 的关键字. 而非 SQL 的. 
	 */
	@Test
	public void testHQLFetch(){
		String hql = "SELECT c FROM Category c LEFT OUTER JOIN FETCH c.items";
		
		List<Category> categories = session.createQuery(hql).list();
		for(Category category: categories){
			System.out.println(category.getCategoryName());
			System.out.println("\n");
			
			System.out.println(category.getItems().size());
		}
	}
	
	/**
	 * set 节点或 many-to-one 节点的 fetch 属性. 
	 * select: 通过额外发送一条 SELECT 语句初始化关联的属性. 
	 * join: 在查询当前对象的同时通过 JOIN 把关联的属性直接进行初始化!
	 * 
	 * 1. 默认情况下, 关联的属性使用懒加载策略. 
	 * 2. 若设置 fetch=join, 则将忽略 lazy。 
	 */
	@Test
	public void testFetch(){
		Category category = (Category) session.get(Category.class, 3);
		System.out.println(category.getCategoryName());
		
		System.out.println("---------------------------------------------");  
		System.out.println(category.getItems().size());
	}
	
	/**
	 * 1. 多对多的关联关系必须使用中间表!
	 * 2. 中间表的样子: 只有两个列, 是两个多对多关联关系的实体类所对应的数据表的外键. 
	 * 且他们还组成了联合主键: 即这两列的组合是不能够重复的. 
	 * 3. 映射的方式:
	 * 
	 * <set name="属性名" table="中间表的表名">
	 * 	<key colomn="当前数据表在中间表的外键"/>
	 * 	<many-to-many class="关联持久化类的全类名" column="联持久化类所对应的数据表在中间表的外键" />
	 * </set>
	 * 
	 * 4. 多对多关联关系必须有一方放弃维护关联关系. 即一方的 set 的 inverse 属性设置为 true
	 */
	@Test
	public void testManyToMany(){
		Item i1 = new Item();
		i1.setItemName("AA");
		
		Item i2 = new Item();
		i2.setItemName("BB");
		
		Category c1 = new Category();
		c1.setCategoryName("aa");
		
		Category c2 = new Category();
		c2.setCategoryName("bb");
		
		//设置关联关系
		i1.getCategories().add(c1);
		i1.getCategories().add(c2);
		
		i2.getCategories().add(c1);
		i2.getCategories().add(c2);
		
		c1.getItems().add(i1);
		c1.getItems().add(i2);
		
		c2.getItems().add(i1);
		c2.getItems().add(i2);
		
		//执行保存
		session.save(c1);
		session.save(c2);
		
		session.save(i1);
		session.save(i2);
	}
}
