package com.atguigu.validation.action;

import com.opensymphony.xwork2.ActionSupport;

public class ValidationAction05 extends ActionSupport{
	
	private static final long serialVersionUID = 1L;
	
	private int age;
	private int grade;
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public int getAge() {
		return age;
	}
	
	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String execute() {
		System.out.println("age="+age);
		System.out.println("grade="+grade);
		return "success";
	}

}
