package com.atguigu.springdata.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Table(name="tbl_person")
@Entity
public class Person
{
	private Integer id;
	private String 	name;
	private String 	email;
	private int 	age;
	private Date 	birth;
	private Address address;
	private String  addressCity;
	
	
	public Person(){}

	
	
	public Person(String name, String email, int age, Date birth)
	{
		super();
		this.name = name;
		this.email = email;
		this.age = age;
		this.birth = birth;
	}



	@Id
	@GeneratedValue
	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public int getAge()
	{
		return age;
	}

	public void setAge(int age)
	{
		this.age = age;
	}

	@Temporal(TemporalType.DATE)
	public Date getBirth()
	{
		return birth;
	}

	public void setBirth(Date birth)
	{
		this.birth = birth;
	}

	@JoinColumn(name="address_id")
	@ManyToOne
	public Address getAddress()
	{
		return address;
	}



	public void setAddress(Address address)
	{
		this.address = address;
	}



	public String getAddressCity()
	{
		return addressCity;
	}



	public void setAddressCity(String addressCity)
	{
		this.addressCity = addressCity;
	}



	@Override
	public String toString()
	{
		return "\n Person [id=" + id + ", name=" + name + ", email=" + email
				+ ", age=" + age + ", birth=" + birth + "]";
	}
	
	
	
}
