package com.atguigu.spring.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.atguigu.spring.beans.Person;

public class SpringTest {

	private ApplicationContext ctx = null;
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	{
		ctx = new ClassPathXmlApplicationContext("beans.xml");
		jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");
		namedParameterJdbcTemplate = (NamedParameterJdbcTemplate) ctx.getBean("namedParameterJdbcTemplate");
	}
	
	@Test
	public void testNamedParameterJdbcTemplate2(){
		String sql = "INSERT INTO hibernate_orders "
				+ "(order_number, order_date, customer_id) "
				+ "VALUES(:orderNumber,:orderDate,:customer.id)";
		Customer customer = new Customer();
		customer.setId(3);

		Order order = new Order();
		order.setCustomer(customer);
		order.setOrderDate(new Date());
		order.setOrderNumber("9001");
		
		save(sql, order);
	}
	
	/*
	 * 也可以调用 NamedParameterJdbcTemplate#update(String sql, SqlParameterSource paramSource)
	 * 其中 SqlParameterSource 的实现类: BeanPropertySqlParameterSource 可以通过 getter 方法来获取
	 * bean 中和据名参数对应的参数的值. 
	 */
	public void save(String sql, Object bean){
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(bean);
		namedParameterJdbcTemplate.update(sql, paramSource);
	}
	
	//使用 NamedParameterJdbcTemplate 可以在 SQL 中加入 命名参数. 
	@Test
	public void testNamedParameterJdbcTemplate(){
		String sql = "INSERT INTO hibernate_orders "
				+ "(order_number, order_date, customer_id) "
				+ "VALUES(:orderNumber,:orderDate,:customerId)";
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderNumber", "8001");
		paramMap.put("orderDate", new Date());
		paramMap.put("customerId", 2);
		
		namedParameterJdbcTemplate.update(sql, paramMap);
	}
	
	//返回单个值, 使用 queryForObject(String sql, Class<Order> requiredType, Object... args) 方法. 
	@Test
	public void testQueryForObject2(){
		String sql = "SELECT count(id) FROM hibernate_orders";
		
		long count = jdbcTemplate.queryForObject(sql, Long.class);
		System.out.println(count);
	}

	/**
	 * 1. 不能调用 queryForObject(String sql, Class<Order> requiredType, Object... args) 方法来获取
	 * 一个 Bean 的实例. 
	 * 2. 应该使用 queryForObject(String sql, RowMapper<Order> rowMapper, Object... args)
	 * 3. RowMapper 可以完成数据表的行到 bean 的转换. 相当于 dbutils 中的 ResultSetHandler. 
	 * BeanPropertyRowMapper: 创建对象时可以传入 mappedClass
	 * 4. 不支持使用级联属性
	 * 5. 可以调用 query 方法返回一个集合. 
	 */
	@Test
	public void testQuery(){
		String sql = "SELECT id, order_number as \"orderNumber\", order_date as \"orderDate\", customer_id as \"customer.id\" "
				+ "FROM hibernate_orders ";
		
		RowMapper<Order> rowMapper = new BeanPropertyRowMapper<>(Order.class);
		List<Order> orders = (List<Order>) jdbcTemplate.query(sql, rowMapper);
		System.out.println(orders);
	}
	
	@Test
	public void testQueryForObject(){
		String sql = "SELECT id, order_number as \"orderNumber\", order_date as \"orderDate\", customer_id as \"customer.id\" "
				+ "FROM hibernate_orders "
				+ "WHERE id = ?";
		
		//queryForObject(String sql, Class<Order> requiredType, Object... args)
		//Order order = jdbcTemplate.queryForObject(sql, Order.class, 2);
		
		Class mappedClass = Order.class;
		
//		RowMapper rowMapper = null;
		
		ParameterizedBeanPropertyRowMapper rowMapper = new ParameterizedBeanPropertyRowMapper<>();
		rowMapper.setMappedClass(mappedClass);
		
		Order order = jdbcTemplate.queryForObject(sql, rowMapper, 2);
		System.out.println(order);
	}
	
	/**
	 * 批量操作
	 */
	@Test
	public void testBatchUpdate(){
		String sql = "INSERT INTO hibernate_orders "
				+ "(order_number, order_date, customer_id) "
				+ "VALUES(?,?,?)";
		
		//批量操作一条记录, 需要的是一个 Object 类型的数组. 即需要一个 Object [] 对象.
		//批量操作, 则需要 Object [] 的集合. 
		List<Object[]> batchArgs = new ArrayList<>();
		batchArgs.add(new Object[]{"5001", new Date(), 1});
		batchArgs.add(new Object[]{"6001", new Date(), 2});
		batchArgs.add(new Object[]{"7001", new Date(), 3});
		
		jdbcTemplate.batchUpdate(sql, batchArgs);
	}
	
	//使用 update 方法可以完成 INSERT, UPDATE 和 DELETE
	@Test
	public void testUpdate(){
		String sql = "INSERT INTO hibernate_orders "
				+ "(order_number, order_date, customer_id) "
				+ "VALUES(?,?,?)";
		
		jdbcTemplate.update(sql, "5001", new Date(), 3);
	}
	
	@Test
	public void testFactoryBean(){
		Person person = (Person) ctx.getBean("testFactoryBean");
		System.out.println(person);
	}
	
	@Test
	public void testInstanceFactoryMethod() throws ParseException{
//		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//		Date date = dateFormat.parse("1991-11-11 11:11:11");
//		
//		System.out.println(date);
		
		Date date = (Date) ctx.getBean("date");
		System.out.println(date);
	}
	
	@Test
	public void testBeanLifeCycle(){
		ClassPathXmlApplicationContext cpxctx = (ClassPathXmlApplicationContext) ctx;
		cpxctx.close();
	}
	
	@Test
	public void testPropertyPlaceHolder() throws SQLException{
		DataSource dataSource = ctx.getBean(DataSource.class);
		Connection connection = dataSource.getConnection();
		System.out.println(connection);
	}
	
	@Test
	public void testScope() {
		Person person1 = (Person) ctx.getBean("person");
		Person person2 = (Person) ctx.getBean("person");
		
		System.out.println(person1 == person2);
	}

}
