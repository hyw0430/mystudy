package com.atguigu.hibernate.many2many;

import java.util.HashSet;
import java.util.Set;

public class Item {

	private Integer id;
	private String itemName;
	
	private Set<Category> categories = new HashSet<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}
}
