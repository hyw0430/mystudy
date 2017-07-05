package com.atguigu.spring.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

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

public class JdbcTest {

	private ApplicationContext ctx = null;
	private JdbcTemplate jdbcTemplate = null;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate = null;
	
	{
		ctx = new ClassPathXmlApplicationContext("jdbc.xml");
		jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");
		namedParameterJdbcTemplate = (NamedParameterJdbcTemplate) ctx.getBean("namedParameterJdbcTemplate");
	}
	
	/**
	 * 使用 SqlParameterSource 作为参数, 
	 * 可以模拟出 hibernate save 方法的效果. 
	 * 但更加的灵活, 应为可以编写 SQL.
	 */
	@Test
	public void testNamedParameterJdbcTemplate(){
		String sql = "INSERT INTO hibernate_orders ("
				+ "order_number, order_date, customer_id) "
				+ "VALUES(:orderNumber, :orderDate, :customer.id)";
		
		Order order = new Order();
		order.setOrderDate(new Date());
		order.setOrderNumber("10001");
		Customer customer = new Customer();
		customer.setId(2);
		order.setCustomer(customer);
		
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(order);
		namedParameterJdbcTemplate.update(sql, paramSource);
	}
	
	/**
	 * 1. 使用 RowMapper 完成数据表的字段和 JavaBean 的属性之间的转换.
	 * 2. 若 JavaBean 的属性名 和 数据表的 列名 都使用标准的方式来命名, 实际上也可以不使用别名. 但依然建议使用别名. 
	 * 3. 不能完成级联属相的查询. 
	 * 4. 查询集合, 使用 query 方法. 
	 */
	@Test
	public void testQueryForObject(){
		String sql = "SELECT id, order_number, order_date, customer_id as \"customer.id\""
				+ "FROM hibernate_orders "
				+ "WHERE id > ?";
		System.out.println(sql);
		
		RowMapper<Order> rowMapper = new BeanPropertyRowMapper<>(Order.class);
//		Order order = jdbcTemplate.queryForObject(sql, rowMapper, 2);
//		System.out.println(order);
		
		List<Order> orders = jdbcTemplate.query(sql, rowMapper, 2);
		System.out.println(orders);
	}
	
	@Test
	public void testDataSource() throws SQLException {
		DataSource dataSource = ctx.getBean(DataSource.class);
		Connection connection = dataSource.getConnection();
		
		System.out.println(connection);
	}

}
