package com.ultra.hibernate.model;

public class Detail {

	private Integer size;
	private String name;

	public Detail() {}

	public Detail(Integer size, String name) {
		super();
		this.size = size;
		this.name = name;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
