package com.ultra.hibernate.model;

import java.util.HashSet;
import java.util.Set;

public class Product {

	private Integer productId;
	private String productName;
	private Set<Item> items = new HashSet<Item>();

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}

}
