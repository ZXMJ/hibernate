package com.ultra.hibernate.model;

public class Teacher extends Person {

	private String subject;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Override
	public String toString() {
		return "Teacher [subject=" + subject + "]";
	}
	
}
