package com.atguigu.hibernate.test;

import java.sql.Blob;
import java.util.Date;

public class Student {
	
	private Integer id;
	private String lastName;
	
	private String email;
	private Date birth;
	
	private Blob picture;

	public Student() {
		// TODO Auto-generated constructor stub
	}
	
	public Student(String lastName, String email) {
		super();
		this.lastName = lastName;
		this.email = email;
	}

	public Student(Integer id, String lastName, String email) {
		super();
		this.id = id;
		this.lastName = lastName;
		this.email = email;
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Date getBirth() {
		return birth;
	}
	
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	
	public Blob getPicture() {
		return picture;
	}

	public void setPicture(Blob picture) {
		this.picture = picture;
	}



	private String info;
	
	public void setInfo(String info) {
		this.info = info;
	}
	
	//类似于: id:1, lastName:BB, email:bb@163.com
	//但数据表中其实并没有 info 对应的数据列. 所以称作派生属性. 
	//可以使用 formular 来进行映射
	public String getInfo() {
		return info;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", lastName=" + lastName + ", email="
				+ email + "]";
	}
}
