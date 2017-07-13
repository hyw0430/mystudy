package com.atguigu.helloworld;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {

	private ApplicationContext ctx = null;
	
	{
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	
	@Test
	public void testP(){
		Person person = (Person) ctx.getBean("testP");
		System.out.println(person.getLastName());
		System.out.println(person.getCar());
	}
	
	@Test
	public void testPropertiesProperty(){
		Person person = (Person) ctx.getBean("testPropertiesProperty");
		System.out.println(person.getInterestings());
	}
	
	@Test
	public void testCollectionProperty(){
		Person person = (Person) ctx.getBean("testCollectionProperty2");
		System.out.println(person.getFriends());
	}
	
	@Test
	public void testCascadeProperty(){
		Person person = (Person) ctx.getBean("wsc2");
		System.out.println(person);
	}
	
	@Test
	public void testNullValue(){
		Car car = (Car) ctx.getBean("testNull");
		System.out.println(car);
	}
	
	@Test
	public void testInnerBean(){
		Person person = (Person) ctx.getBean("wsc");
		System.out.println(person);
	}
	
	@Test
	public void testRef(){
		CarAction carAction = (CarAction) ctx.getBean("carAction");
		carAction.execute();
	}
	
	
	@Test
	public void testConstructorArg(){
		Car car = (Car) ctx.getBean("car");
		System.out.println(car);
	}
	
	@Test
	public void testGetBean() {
		//传入 bean 在配置文件中配置的 id, 根据 id 返回实际的 bean. 
		//HelloWorld helloWorld = (HelloWorld) ctx.getBean("helloWorld");
		
		//传入 bean 的类型, 根据类型返回实际的 bean.
		//若 IOC 容器中有多个对应的类型的 bean, 则该方法会出现异常:NoUniqueBeanDefinitionException
		HelloWorld helloWorld = ctx.getBean(HelloWorld.class);
		helloWorld.sayHello();
	}

}
