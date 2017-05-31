package com.ultra.hibernate.model;

public class Emp {

	private Integer empId;
	private String empName;
	private float sal;
	private Department department;

	public Emp() {}

	public Emp(Integer empId, String empName) {
		super();
		this.empId = empId;
		this.empName = empName;
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public float getSal() {
		return sal;
	}

	public void setSal(float sal) {
		this.sal = sal;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Emp [empId=" + empId + ", empName=" + empName + "]";
	}

}
