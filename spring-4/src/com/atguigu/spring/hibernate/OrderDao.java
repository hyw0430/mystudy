package com.atguigu.spring.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	//获取和当前线程绑定的 Session!
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	public void save(Order order){
		this.getSession().save(order);
	}
}
