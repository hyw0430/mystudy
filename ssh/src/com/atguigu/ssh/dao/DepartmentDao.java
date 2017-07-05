package com.atguigu.ssh.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.atguigu.ssh.domains.Department;

@Repository
public class DepartmentDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	public List<Department> getAll(){
		String hql = "FROM Department";
		Query query = getSession().createQuery(hql);
		query.setCacheable(true);
		return query.list();
//		return this.getSession().createQuery("FROM Department").setCacheable(true).list();
	}
	
}
