package com.atguigu.hibernate.many2many;

import java.util.HashSet;
import java.util.Set;

public class Category {

	private Integer id;
	private String categoryName;
	
	private Set<Item> items = new HashSet<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}
}
