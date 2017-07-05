package com.atguigu.convert.action;

import com.atguigu.convert.bean.Employee;
import com.opensymphony.xwork2.ModelDriven;

public class Conversion04 implements ModelDriven<Employee>{
	
	private Employee employee;

	@Override
	public Employee getModel() {
		this.employee = new Employee();
		return this.employee;
	}
	
	public String execute() {
		
		System.out.println(employee);
		
		return "success";
	}

}
