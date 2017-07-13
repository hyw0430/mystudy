package com.atguigu.hibernate.test;

import java.util.Date;

public class Order {

	private Integer id;
	private String orderNumber;
	
	private Date orderDate;
	private Customer customer;
	
	public Order() {
		// TODO Auto-generated constructor stub
	}

	public Order(String orderNumber, Date orderDate) {
		super();
		this.orderNumber = orderNumber;
		this.orderDate = orderDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}



	public String info;
	
	//希望返回的格式为: "[Order] id:1, orderNumber:1001,orderDate:xxx"
	//实际上对于 info 字段可以由其他字段计算得到. 所以不需要在数据表中添加那样的一列. 这样的属性称为 派生属性
	//在映射文件中使用 formular 来进行映射. 
	//实际查询的时候派生属性是一个子查询. 
	public String getInfo() {
		return info;
	}
	
	public void setInfo(String info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + "]";
	}
}
