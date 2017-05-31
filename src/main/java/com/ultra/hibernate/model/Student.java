package com.ultra.hibernate.model;

public class Student {

	private Integer studentId;
	private String studentName;
	private School school;

	public Student() {
	}

	public Student(String studentName, School school) {
		this.studentName = studentName;
		this.setSchool(school);
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", studentName=" + studentName + ", school=" + school + "]";
	}

}
