package com.atguigu.springdata.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.springdata.dao.PersonRepository;


@Service
public class PersonService
{
	@Autowired
	private PersonRepository personRepository;
	
	@Transactional
	public int updateEmail(String email,Integer id)
	{
		return personRepository.updateEmail(email, id);
	}
	
}
