package com.atguigu.spring;



public class PersonAction {

	private PersonService personService;
	
	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}
	
	public void execute(){
		System.out.println("[PersonAction]#execute");
		personService.save();
	}
	
}
