package com.ultra.hibernate.model;

import java.util.HashSet;
import java.util.Set;

public class Item2 {

	private Integer itemId;
	private String itemName;
	private Set<Product2> products = new HashSet<Product2>();

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Set<Product2> getProducts() {
		return products;
	}

	public void setProducts(Set<Product2> products) {
		this.products = products;
	}

}
