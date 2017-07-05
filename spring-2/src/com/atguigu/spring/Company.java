package com.atguigu.spring;

public class Company {

	public Company() {
		System.out.println("公司创建了!");
	}
	
	public void init(){
		System.out.println("前期经营!");
	}
	
	public void work(){
		System.out.println("日常运营!");
	}
	
	public void destroy(){
		System.out.println("公司倒闭!");
	}
	
	private String name;
	
	public void setName(String name) {
		System.out.println("为属性赋值!");
		this.name = name;
	}
}
