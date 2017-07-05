package com.atguigu.ssh.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.atguigu.ssh.domains.Employee;

@Repository
public class EmployeeDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession(){
		return this.sessionFactory.getCurrentSession();
	}
	
	public Employee get(Integer id){
		String hql = "SELECT e "
				+ "FROM Employee e "
				+ "LEFT OUTER JOIN FETCH e.department "
				+ "WHERE e.id = ?";
		Query query = getSession().createQuery(hql).setParameter(0, id);
		return (Employee) query.uniqueResult();
	}
	
	public void save(Employee employee){
		getSession().saveOrUpdate(employee);
	}
	
	public List<Employee> getAll(){
		String hql = "SELECT e "
				+ "FROM Employee e "
				+ "LEFT OUTER JOIN FETCH e.department ";
		Query query = getSession().createQuery(hql);
		
		return query.list();
	}
}
