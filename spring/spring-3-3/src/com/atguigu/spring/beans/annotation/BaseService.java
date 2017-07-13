package com.atguigu.spring.beans.annotation;

import org.springframework.beans.factory.annotation.Autowired;

public class BaseService<T> {

	@Autowired
	private BaseDao<T> dao;
	
	public void save(){
		System.out.println(this.getClass().getName() + "#save: " + this.dao.getClass().getName());
	}
	
}
