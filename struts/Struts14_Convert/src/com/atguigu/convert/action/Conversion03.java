package com.atguigu.convert.action;

import com.atguigu.convert.bean.Address;

public class Conversion03 {
	
	private Address address;
	
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public String execute() {
		System.out.println(address);
		return "success";
	}

}
