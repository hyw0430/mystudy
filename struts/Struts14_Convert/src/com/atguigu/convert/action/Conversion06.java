package com.atguigu.convert.action;

import java.util.List;

import com.atguigu.convert.bean.Employee;

public class Conversion06 {
	
	private List<Employee> empList;
	
	public void setEmpList(List<Employee> empList) {
		this.empList = empList;
	}
	
	public List<Employee> getEmpList() {
		return empList;
	}
	
	public String execute() {
		for (Employee employee : empList) {
			System.out.println(employee);
		}
		return "success";
	}

}
