package com.atguigu.spring.beans;

import org.springframework.beans.factory.annotation.Autowired;

public class Person {
	
	public Person() {
		System.out.println("Person's Constructor...");
	}
	
	private String lastName;
	
	public void setLastName(String lastName) {
		System.out.println("setLastName: " + lastName);
		this.lastName = lastName;
	}
	
	public void init(){
		System.out.println("init");
	}
	
	public void destroy(){
		System.out.println("destroy");
	}
	
}
