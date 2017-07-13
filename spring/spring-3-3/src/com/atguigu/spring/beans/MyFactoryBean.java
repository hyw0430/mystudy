package com.atguigu.spring.beans;

import org.springframework.beans.factory.FactoryBean;

public class MyFactoryBean implements FactoryBean<Person> {

	
	//实际返回的 bean 的实例.
	@Override
	public Person getObject() throws Exception {
		Person person = new Person();
		person.setLastName("JavaScript");
		
		return person;
	}

	//返回的 bean 的类型
	@Override
	public Class<?> getObjectType() {
		return Person.class;
	}

	//返回的 bean 是否为单例的.
	@Override
	public boolean isSingleton() {
		return true;
	}

}
