package com.atguigu.spring.test;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atguigu.spring.Company;
import com.atguigu.spring.Person;
import com.atguigu.spring.PersonAction;

public class SpringTest {

	private ApplicationContext ctx = null;
	
	{
		ctx = new ClassPathXmlApplicationContext("beans.xml");
	}
	
	@Test
	public void testFactoryBean(){
		Object object = ctx.getBean("testFactoryBean");
		System.out.println(object);
	}
	
	@Test
	public void testStaticFactoryMethod(){
		DateFormat timeFormat = (DateFormat) ctx.getBean("timeFormat");
		Object result = timeFormat.format(new Date());
		System.out.println(result);
	}
	
	@Test
	public void testInstanceFactoryMethod() throws ParseException{
		Person person = (Person) ctx.getBean("testStaticFactoryMethod");
		System.out.println(person.getBirth());
		
//		String dateStr = "1990-12-12";
//		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		Date date = dateFormat.parse(dateStr);
//		System.out.println(date);
	}
	
	@Test
	public void testBeanLifeCycle(){
		Company company = (Company) ctx.getBean("company");
		company.work();
		
		ConfigurableApplicationContext cctx = (ConfigurableApplicationContext) ctx;
		cctx.close();
	}
	
	@Test
	public void testSpEL(){
		Person person = (Person) ctx.getBean("yangguo");
		System.out.println(person.getCar());
	}
	
	@Test
	public void testPropertyHolder() throws SQLException{
		DataSource dataSource = ctx.getBean(DataSource.class);
		System.out.println(dataSource.getConnection());
	}
	
	@Test
	public void testScope(){
		Person person1 = (Person) ctx.getBean("testScope");
		Person person2 = (Person) ctx.getBean("testScope");
		
		System.out.println(person1 == person2);
	}
	
	@Test
	public void testDepends(){
		
	}
	
	@Test
	public void testParent(){
		Person person = (Person) ctx.getBean("jerry");
		System.out.println(person);
	}
	
	@Test
	public void testProperties(){
		Person person = (Person) ctx.getBean("testProperties");
		System.out.println(person.getProperties());
	}
	
	@Test
	public void testInnerBean(){
		Person person = (Person) ctx.getBean("ms");
		System.out.println(person);
	}
	
	@Test
	public void testRef() {
		PersonAction personAction = (PersonAction) ctx.getBean("personAction");
		personAction.execute();
	}

}
