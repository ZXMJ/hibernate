package com.ultra.hibernate.model;

import java.util.HashSet;
import java.util.Set;

public class School {

	private Integer schoolId;
	private String schoolName;
	//
	private Set<Student> students = new HashSet<Student>();

	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	@Override
	public String toString() {
		return "School [schoolId=" + schoolId + ", schoolName=" + schoolName + ", students=" + students + "]";
	}

}
