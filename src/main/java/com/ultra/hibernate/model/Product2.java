package com.ultra.hibernate.model;

import java.util.HashSet;
import java.util.Set;

public class Product2 {

	private Integer productId;
	private String productName;
	private Set<Item2> items = new HashSet<Item2>();

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

	public Set<Item2> getItems() {
		return items;
	}

	public void setItems(Set<Item2> items) {
		this.items = items;
	}

}
