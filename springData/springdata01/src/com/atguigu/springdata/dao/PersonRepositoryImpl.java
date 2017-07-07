package com.atguigu.springdata.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class PersonRepositoryImpl implements PersonDao
{
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void dealMethod()
	{
		System.out.println("**********: "+entityManager.getClass().getName());
	}
}
