package com.ultra.hibernate.model;

import java.util.HashSet;
import java.util.Set;

public class Department {

	private Integer departmentId;
	private String departName;
	private Set<Emp> emps = new HashSet<Emp>();

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

	public Set<Emp> getEmps() {
		return emps;
	}

	public void setEmps(Set<Emp> emps) {
		this.emps = emps;
	}

	@Override
	public String toString() {
		return "Department [departmentId=" + departmentId + ", departName=" + departName + "]";
	}

}
