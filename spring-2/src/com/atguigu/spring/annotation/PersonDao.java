package com.atguigu.spring.annotation;

import org.springframework.stereotype.Repository;

@Repository
public class PersonDao {

	public void save(){
		System.out.println("[PersonDao]#save");
	}
	
}
