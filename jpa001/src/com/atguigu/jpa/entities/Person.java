package com.atguigu.jpa.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Table(name="tbl_person")
@Entity
public class Person {
	private Integer id;
	private String personName;
	private int age;
	private double salary;
	private Date birth;
	private Date registerTime;

	public Person() {
		super();
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(length=40,nullable=false)
	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	@Transient
	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}
	@Temporal(TemporalType.TIMESTAMP)
	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", personName=" + personName + ", age=" + age + ", salary=" + salary + ", birth="
				+ birth + ", registerTime=" + registerTime + "]";
	}
	@Transient //∆¡±Œjpaπ‹¿Ì
	public String getPersonInfo(){
		
		return "jap hello";
	}

}
