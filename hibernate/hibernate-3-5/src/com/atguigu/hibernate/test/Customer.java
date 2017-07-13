package com.atguigu.hibernate.test;

import java.util.HashSet;
import java.util.Set;

public class Customer {

	private Integer id;
	private String lastName;
	
	//映射多的关联关系时, 类型必须使用接口类型!
	private Set<Order> orders = new HashSet<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
}
