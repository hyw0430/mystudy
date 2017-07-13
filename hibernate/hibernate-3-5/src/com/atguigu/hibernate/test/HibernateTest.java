package com.atguigu.hibernate.test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
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
	
	//1. fetch 对于 HQL 不起作用! 只要 HQL 链接其他实体类通常都需要使用 FETCH. 
	//2. 若使用左外连接或内连接进行查询(不带 FETCH 关键字), 需要使用 SELECT 子句. 
	//否则返回的是 Object[] 对象(的集合). 还是需要额外发送一条 SQL 来查询其关联的对象. 
	//3. 使用 FETCH: 可以不适用 SELECT 子句, 而且返回的结果中关联的对象也会被初始化. 但建议使用 SELECT 子句. 
	
	//4. 对于 get 方法, 若设置了 fetch 属性, 则将忽略 lazy 属性.
	//5. lazy 属性对于 HQL 是起作用的. 
	@Test
	public void testLazyFetchAndHQL(){
		//Order order = (Order) session.get(Order.class, 2);
		
//		String hql = "SELECT o FROM Order o LEFT OUTER JOIN FETCH o.customer WHERE o.id = :id";
//		Order order = (Order) session.createQuery(hql).setParameter("id", 2).uniqueResult();
		
		String hql = "FROM Order o WHERE o.orderNumber = :on";
		Order order = (Order) session.createQuery(hql).setParameter("on", "1001").uniqueResult();
		
		System.out.println(order.getOrderNumber());
		System.out.println("-----------------------------------");
		System.out.println(order.getCustomer().getLastName());
	
	}
	
	/**
	 * 除了使用 HQL 查询以外, 还可以使用 QBC 查询.
	 * QBC: Query By Criteria.
	 * 
	 * 通过 Hibernate 提供的一组 API 来取代 HQL. 
	 * 
	 * QBC vs HQL:
	 * HQL 需要编写 HQL 语句, 所以更加的灵活. 
	 * QBC: 更加利于模块化. 可以编写出和具体实现类无关的 BaseDAO. 
	 * 
	 * 更多例子参见: documentation/manual/en-US/html_single/index.html#querycriteria
	 */
	@Test
	public void testQBC(){
		//1. 创建 Criteria 对象. 
		Criteria criteria = session.createCriteria(Order.class);
		
		//2. Criterion 代表查询条件. 
		//Criterion 对象可以有 Restrictions 静态工厂方法得到
		Criterion criterion = Restrictions.eq("orderNumber", "1001");
		
		//3. 添加查询条件
		criteria.add(criterion);
		
		//4. 执行查询. 
		Order order = (Order) criteria.uniqueResult();
		System.out.println("-->" + order);
	}
	
	//1. 默认情况下, 若只查询 Customer, 不会把其关联的 Order 也查询出来.
	//2. 在 HQL 中可以使用 DISTINCT
	//3. 若使用 LEFT OUTER JOIN 则将会连接数据表. 查询出 Customer 关联的所有 Order. 但是, Customer 的 orders
	//集合不会被初始化. 
	//4. 若在 JOIN 的后面添加 FETCH 关键字, 则会使查询的 Customer 的 Order 集合也被初始化!
	//5. 使用了 FETCH 关键字的 HQL 将更加灵活. 可以动态的在 HQL 语句中覆盖 fetch 属性. 也可以说 fetch 属性对于 HQL 是无效的.
	//6. 也可使用 INNER JOIN FETCH, 此时左表不满足连接条件的记录将不会被查询出来. 
	@Test
	public void testHQLFetch(){
		//在查询 Customer 的同时, 把其关联的 Order 集合也都查询出来.
		String hql = "SELECT DISTINCT c "
				+ "FROM Customer c "
				+ "LEFT OUTER JOIN FETCH c.orders "
			  //+ "INNER JOIN FETCH c.orders "
				+ "WHERE c.id = ?";
		
		Query query = session.createQuery(hql).setParameter(0, 1);
		Customer customer = (Customer) query.uniqueResult();
		
		System.out.println(customer.getLastName());
		System.out.println(customer.getOrders());
	}
	
	//HQL 支持使用子查询. 
	@Test
	public void testSubQuery(){
		//查询出 customer 的 lastName 为 "AAA" 的所有 Order
		String hql = "FROM Order o WHERE o.customer = (SELECT c FROM Customer c WHERE c.lastName = ?)";
		Query query = session.createQuery(hql).setParameter(0, "AAA");
		
		List<Order> orders = query.list();
		System.out.println(orders);
		
		/*
		String hql = "FROM Order o WHERE o.customer.lastName = ?";
		Query query = session.createQuery(hql).setParameter(0, "AAA");
		
		List<Order> orders = query.list();
		System.out.println(orders);
		*/
	}
	
	//支持使用统计函数
	@Test
	public void testSQLFunction(){
		String hql = "SELECT max(o.id) FROM Order o";
		System.out.println(session.createQuery(hql).uniqueResult());
	}
	
	//只查询部分属性.
	//1. 不常用
	//2. 需要使用 SELECT 子句
	//3. 默认情况下, 返回的是 Object[] (的集合)
	//4. 若希望返回的实体类的对象, 则需要在对应的实体类中添加对应的构造器. 
	@Test
	public void testQueryProperties(){
		String hql = "SELECT new com.atguigu.hibernate.test.Order(o.orderNumber, o.orderDate) "
				+ "FROM Order o ";
		List<Order> orders = session.createQuery(hql).list();
		for(Order order: orders){
			System.out.println(order.getOrderNumber() + ":" + order.getOrderDate());
		}
		
		/*
		String hql = "SELECT o.orderNumber, o.orderDate "
				+ "FROM Order o ";
		
		List<Object[]> result = session.createQuery(hql).list();
		for(Object [] objs: result){
			System.out.println(Arrays.asList(objs));
		}
		*/
	}
	
	//可以把 HQL 语句配置在配置文件中. 而不是直接写死在程序中. 
	@Test
	public void testNamedQuery(){
		Query query = session.getNamedQuery("getAll");
		query.setParameter(0, 1).setParameter(1, 3);
		
		List<Order> orders = query.list();
		System.out.println(orders);
	}
	
	@Test
	public void testSetPageParameter(){
		String hql = "FROM Order o";
		
		Query query = session.createQuery(hql);
		
		//设置分页参数.
		int pageNo = 1;
		int pageSize = 4;
		
		int firstResult = (pageNo - 1) * pageSize;
		int maxResults = pageSize;
		//setFirstResult: 设置起始的索引. 从 0 开始
		//setMaxResults: 设置每页显示多少条记录. 
		query.setFirstResult(firstResult).setMaxResults(maxResults);
		
		List<Order> orders = query.list();
		System.out.println(orders);
	}
	
	@Test
	public void testBindingParameter(){
		//使用具名参数: 即参数有名字
		//使用 :参数名 的方式来替代之前的 ? 然后再后面调用 setParameter 方法时
		//不再传入占位符的位置, 而是传入具名参数的参数名
		String hql = "FROM Order o WHERE o.orderNumber = :on "
				+ "AND o.customer.lastName = :ln";
		
		Order order = (Order) session.createQuery(hql)
						             .setParameter("ln", "AAA")
						             .setParameter("on", "1001")
						             .uniqueResult();
		System.out.println(order.getInfo());
		
		/*	
		//使用占位符的方式
		String hql = "FROM Order o WHERE o.orderNumber = ? "
				+ "AND o.customer.lastName = ?";
		
		Order order = (Order) session.createQuery(hql)
						             .setString(0, "1001")
						             .setParameter(1, "AAA")
						             .uniqueResult();
		System.out.println(order.getInfo());
		*/
	}
	
	@Test
	public void testHelloHQL(){
		//1. 编写 HQL 语句
		//HQL 语句面对的类和类的属性. 而不是表和列名
		String hql = "FROM Order o WHERE o.orderNumber = ?";
		
		//2. 创建 Query 对象.
		//注意: Query 支持方法链的编程风格. 即调用其 setXxx 方法后返回的不是 void
		//而是 Query 对象. 
		Order order = (Order) session.createQuery(hql)
				                     .setString(0, "1001")
				                     .uniqueResult();
		System.out.println(order);
		
		/*
		Query query = session.createQuery(hql);
		
		//3. 设置占位符的值
		query.setString(0, "1001");
		
		//4. 执行查询
		Order order = (Order) query.uniqueResult();
		System.out.println(order.getInfo());
		*/
	}
	
	/**
	 * fetch: 设置加载关联的对象的方式.
	 * select: 通过额外发送一条 SQL 的方式来获取关联的对象.
	 * join: 通过一条左外连接的语句把当前和关联的对象一起获取出来. 此时忽略 lazy
	 */
	@Test
	public void testFetch2(){
		Order order = (Order) session.get(Order.class, 2);
		System.out.println(order.getOrderNumber());
		
		System.out.println("--------------------------------");
		System.out.println(order.getCustomer().getLastName());
	}
	
	/**
	 * 可以通过设置 many-to-one 或 one-to-one 的 lazy 属性来修改关联属性的加载策略.
	 * 通常情况下不需要来修改其默认值. proxy 是默认值
	 */
	@Test
	public void testLazy2(){
		Order order = (Order) session.get(Order.class, 2);
		System.out.println(order.getOrderNumber());
		
		System.out.println("--------------------------------");
		System.out.println(order.getCustomer().getLastName());
	}
	
	/**
	 * 了解: 
	 * 在 fetch 取值不是 subselect 的情况下, 
	 * 若有 n 多个集合需要被初始化, 则一次初始化 batch-size 个.
	 * 
	 * 若 fetch 取值为 subselect, 则 batch-size 无效. 
	 */
	@Test
	public void testBatchSize(){
		String hql = "FROM Customer c";
		List<Customer> customers = session.createQuery(hql).list();
		
		for(Customer customer: customers){
			System.out.println(customer.getOrders().size());
		}
	}
	
	/**
	 * 若使用了 HQL 查询多 1 的一端的 bean 的集合. 则 fetch 若取值为 subselect, 则可以通过一条带有子查询的 SQL 语句
	 * 初始化所有的 1 的一端关联的集合属性
	 * 
	 * 若使用 get 方法查询多个 1 的一端的 bean, 则 fetch 设置为 subselect 无效. 
	 */
	@Test
	public void testSubSelect(){
		String hql = "FROM Customer c";
		List<Customer> customers = session.createQuery(hql).list();
		
		System.out.println(customers.get(0).getOrders().size());
		
//		for(Customer customer: customers){
//			System.out.println(customer.getOrders().size());
//		}
		
		/*
		Customer customer1 = (Customer) session.get(Customer.class, 1);
		Customer customer2 = (Customer) session.get(Customer.class, 2);
		Customer customer3 = (Customer) session.get(Customer.class, 3);
		
		System.out.println(customer1.getOrders().size());
		System.out.println(customer2.getOrders().size());
		System.out.println(customer3.getOrders().size());
		*/
	}
	
	/**
	 * fetch: 抓取. 配置初始化集合属性的方式.
	 * select: 默认值, 即通过额外发送一条 SQL 的方式来初始化集合属性. 
	 * join: 只发送一条 SQL 语句. 即通过左外连接的方式在查询 1 的同时初始化 n 的集合. 此时会忽略 lazy 属性
	 * subselect: 了解. 通过子查询的方式来初始化集合属性. 
	 */
	@Test
	public void testFetch(){
		Customer customer = (Customer) session.get(Customer.class, 2);
		System.out.println(customer.getLastName());
		
		System.out.println("-------------------------------------");
		System.out.println(customer.getOrders().size());
	}
	
	/**
	 * 默认情况下, 一对多或多对多的多使用懒加载策略. 
	 * 实际上可以通过修改 set 的lazy 属性来改变加载策略. 实际开发时很少去修改该属性. 
	 * lazy 取值:
	 * true: 默认值. 懒加载策略
	 * false: 取消懒加载. 即立即加载. 
	 * extra: 进可能的通过发送 SQL 语句延迟集合被初始化的时间. 
	 */
	@Test
	public void testSetLazy(){
		Customer customer = (Customer) session.get(Customer.class, 2);
		System.out.println(customer.getLastName());
		
		System.out.println("-------------------------------------");
		System.out.println(customer.getOrders().size());
	}
	
	/**
	 * load 方法支持类级别的懒加载策略. 
	 * 了解: 可以通过设置 class 节点的 lazy 属性来修改 load 方法的加载策略. 
	 * 但通常不需要设置该属性, 因为若不需要懒加载, 则直接使用 get 即可。
	 * 且 class 上面设置的 lazy 仅对 load 方法其作用. 
	 */
	@Test
	public void testLazy(){
		Order order = (Order) session.load(Order.class, 2);
		System.out.println("----------------------------------");
	}
	
	@Test
	public void testOne2One2(){
		Department dept = (Department) session.get(Department.class, 1);
		System.out.println(dept.getDeptName());
		
		System.out.println(dept.getManager().getLastName());
	}
	
	@Test
	public void testOne2OneGet(){
		Manager mgr = (Manager) session.get(Manager.class, 1);
		System.out.println(mgr.getLastName());
		
		System.out.println("----------------------------");
		System.out.println(mgr.getDept().getDeptName());
	}
	
	@Test
	public void testOne2OneSave(){
		Manager mgr = new Manager();
		mgr.setLastName("AAA");
		
		Department dept = new Department();
		dept.setDeptName("SALES");
		
		//设置关联关系
		mgr.setDept(dept);
		dept.setManager(mgr);
		
		//执行保存
		session.save(dept);
		session.save(mgr);
	}
	
	/**
	 * 懒加载: 只有在使用关联对象的属性时, 才发送 SQL 语句对其进行初始化. 
	 */
	@Test
	public void testMany2OneGet(){
		Order order = (Order) session.get(Order.class, 2);
		System.out.println(order.getInfo());
		
		System.out.println("----------------------------------------------");
		System.out.println(order.getCustomer().getLastName());
	}
	
	/**
	 * 多对一的关联关系:
	 * 1. 尽可能有 多 的一端来维护关联关系. 一的一端放弃维护关联关系. 可以通过设置 set 的 inverse=true 来实现
	 * 2. 在 save 时, 先保存 1 的一端会减少 UPDATE 语句.
	 */
	@Test
	public void testMany2OneSave(){
		Order order1 = new Order("3001", new Date());
		Order order2 = new Order("3002", new Date());
		
		Customer customer = new Customer();
		customer.setLastName("CCC");
		
		//设置关联关系
		order1.setCustomer(customer);
		order2.setCustomer(customer);
		
		customer.getOrders().add(order1);
		customer.getOrders().add(order2);
		
		//执行保存
		session.save(customer);

		session.save(order1);
		session.save(order2);
	}
	
	@Test
	public void testFormular(){
		Order order = (Order) session.get(Order.class, 1);
		System.out.println(order.getInfo());
	}

	@Test
	public void testDate(){
		Order order = new Order();
		order.setOrderDate(new Date());
		order.setOrderNumber("1001");
		
		session.save(order);
	}
	
}
