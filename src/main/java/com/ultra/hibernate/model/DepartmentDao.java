package com.ultra.hibernate.model;

import org.hibernate.Session;

public class DepartmentDao {

	public void save(Department depart){
		Session session = A180HibernateUtils.getInstance().getSession();
		System.out.println(session.hashCode());
		session.save(depart);
	}
}
