package com.atguigu.convert.action;

import com.atguigu.convert.bean.Employee;

public class Conversion05 {
	
	private Employee employee;
	
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public Employee getEmployee() {
		return employee;
	}
	
	public String execute() {
		System.out.println(employee);
		return "success";
	}

}
