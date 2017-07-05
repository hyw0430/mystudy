package com.atguigu.spring;

public class Person {
	
	private String lastName;
	
	public Person() {
		System.out.println("Person's Constructor...");
	}
	
	public void setLastName(String lastName) {
		System.out.println("setLastName: " + lastName);
		this.lastName = lastName;
	}
	
	public String getLastName() {
		return lastName;
	}
}
