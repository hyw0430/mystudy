package com.atguigu.jpa.test;

import java.util.Date;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.atguigu.jpa.entities.Person;

public class Jpa_API {

	private EntityManagerFactory entityManagerFactory = null;
	private EntityManager entityManager = null;
	private EntityTransaction transaction = null;

	@Before
	public void init() {
		String persistenceUnitName = "jpa001";
		entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnitName);
		entityManager = entityManagerFactory.createEntityManager();
		transaction = entityManager.getTransaction();
		transaction.begin();
	}

	@After
	public void release() {
		transaction.commit();
		entityManager.close();
		entityManagerFactory.close();
	}

	@Test
	public void testFind() {
		Person p1 = entityManager.find(Person.class, 1);
		// System.out.println(p1.toString());
	}

	@Test
	public void testGetReference() {// 懒加载。用到p1时候才会真正执行
		Person p1 = entityManager.getReference(Person.class, 1);
		// System.out.println(p1.toString());
		// System.out.println(p1.getClass().getName());
		// System.out.println(p1.getPersonName());
	}

	@Test
	public void testPersist() {
		Person person = new Person();
		person.setAge(27);
		person.setBirth(new Date());
		person.setPersonName("lisi" + UUID.randomUUID().toString().substring(1, 8));
		person.setRegisterTime(new Date());
		person.setSalary(4533.55);

		person.setId(1213);

		entityManager.persist(person);
	}

	@Test
	public void testRemove() {
		// Person p1 = entityManager.find(Person.class, 11);
		// entityManager.remove(p1);

	}

	@Test
	public void testFlush() {
		Person person = entityManager.find(Person.class, 3);
		person.setPersonName("xxx3");
		entityManager.flush();
		System.out.println("----------------------------");
	}

	@Test
	public void testRefresh() {
		Person person = entityManager.find(Person.class, 3);
		person = entityManager.find(Person.class, 3);
		entityManager.refresh(person);
//		entityManager.refresh(person);
//		entityManager.refresh(person);

	}

}
