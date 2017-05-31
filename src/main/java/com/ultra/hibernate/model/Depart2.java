package com.ultra.hibernate.model;

public class Depart2 {

	private Integer departId;
	private String departName;
	private Manager2 manager;

	public Integer getDepartId() {
		return departId;
	}

	public void setDepartId(Integer departId) {
		this.departId = departId;
	}

	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

	public Manager2 getManager() {
		return manager;
	}

	public void setManager(Manager2 manager) {
		this.manager = manager;
	}

}
