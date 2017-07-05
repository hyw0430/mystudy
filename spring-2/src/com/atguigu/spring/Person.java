package com.atguigu.spring;

import java.util.Date;
import java.util.Properties;

public class Person {

	private String lastName;
	private Car car;
	
	private Properties properties;
	private Date birth;
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void setCar(Car car) {
		this.car = car;
	}
	
	public Car getCar() {
		return car;
	}
	
	public void setProperties(Properties properties) {
		this.properties = properties;
	}
	
	public Properties getProperties() {
		return properties;
	}
	
	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getLastName() {
		return lastName;
	}

	@Override
	public String toString() {
		return "Person [lastName=" + lastName + ", car=" + car + "]";
	}
	
}
