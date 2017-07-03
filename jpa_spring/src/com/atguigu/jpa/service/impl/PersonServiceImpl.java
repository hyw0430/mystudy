package com.atguigu.jpa.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.jpa.dao.PersonDao;
import com.atguigu.jpa.entities.Person;
import com.atguigu.jpa.service.PersonService;

@Service
@Transactional(readOnly=true)
public class PersonServiceImpl implements PersonService
{
	@Autowired
	private PersonDao personDao;
	
	@Override
	@Transactional(isolation=Isolation.DEFAULT,
				   propagation=Propagation.REQUIRED,
				   readOnly=false,
				   rollbackFor=Exception.class)
	public void add(Person person) throws IOException
	{
		personDao.add(person);
		
		//int age = 10/0;
		
		FileInputStream input = new FileInputStream(new File("D:\\44\\a\\b\\c0719\\adsfasdf.txt"));
		
	}
	
}
