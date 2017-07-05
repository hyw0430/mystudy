package com.atguigu.spring;


public class PersonService {

	private PersonDao personDao;
	
	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}
	
	public void save(){
		System.out.println("[PersonService]#save");
		personDao.save();
	}
	
}
