package com.atguigu.spring.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

	@Autowired
	private PersonDao personDao;
	
	public void save(){
		System.out.println("[PersonService]#save");
		personDao.save();
	}
	
}
