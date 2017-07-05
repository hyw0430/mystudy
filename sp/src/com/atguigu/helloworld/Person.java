package com.atguigu.helloworld;

import java.util.List;
import java.util.Properties;

public class Person {

	private String lastName;
	private Car car;
	
	private List<Person> friends = null;
	private Properties interestings = null;

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}
	
	public void setFriends(List<Person> friends) {
		this.friends = friends;
	}
	
	public List<Person> getFriends() {
		return friends;
	}
	
	public void setInterestings(Properties interestings) {
		this.interestings = interestings;
	}
	
	public Properties getInterestings() {
		return interestings;
	}
	
	@Override
	public String toString() {
		return "Person [lastName=" + lastName + ", car=" + car + "]";
	}
}
