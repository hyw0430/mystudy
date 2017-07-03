package com.atguigu.jpa.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.atguigu.jpa.dao.PersonDao;
import com.atguigu.jpa.entities.Person;

@Repository
public class PersonDaoImpl implements PersonDao
{
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void add(Person person)
	{
		entityManager.persist(person);
	}	
}
