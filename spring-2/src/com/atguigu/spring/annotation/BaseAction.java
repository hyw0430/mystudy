package com.atguigu.spring.annotation;

import org.springframework.beans.factory.annotation.Autowired;

public class BaseAction<T> {

	@Autowired
	private BaseService<T> service;
	
	public void execute(){
		System.out.println("[" + this.getClass().getName() + "]#save: " 
				+ service.getClass().getName());
		service.save();
	}
}
