package com.atguigu.convert.action;

import com.atguigu.convert.bean.Address;

public class Conversion02 {
	
	//复杂对象
	private Address address;
	
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public Address getAddress() {
		return address;
	}
	
	public String execute() {
		System.out.println(address);
		return "success";
	}

}
