package com.atguigu.spring;

public class PersonAction {

	private Person person;
	
	public void setPerson(Person person) {
		this.person = person;
	}
	
	public String execute(){
		System.out.println(person.getLastName());
		return "success";
	}
	
}
