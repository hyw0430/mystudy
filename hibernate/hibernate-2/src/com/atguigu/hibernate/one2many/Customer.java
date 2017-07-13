package com.atguigu.hibernate.one2many;

import java.util.HashSet;
import java.util.Set;

public class Customer {

	private Integer id;
	private String lastName;
	
	//1.一对多查询时, 返回的集合类型是 Hibernate 提供的集合接口的实现类.
	//例如: PersistentSet. 该类型是集合接口的实现类类型.
	//所以在创建 Customer 对象时, 集合类型需要使用接口类型, 而不能使用具体的某一个实现类的类型. 
	//2. 在编写集合类型的属性时, 建议为其赋值. 可以避免出现空指针异常. 
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
