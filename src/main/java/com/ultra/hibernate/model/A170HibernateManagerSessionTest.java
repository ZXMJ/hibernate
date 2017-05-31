package com.ultra.hibernate.model;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

public class A170HibernateManagerSessionTest {

	@Test
	public void testManagerSession() {
		Session session = A180HibernateUtils.getInstance().getSession();
		System.out.println("-->" + session.hashCode());
		Transaction transaction = session.beginTransaction();

		DepartmentDao departmentDao = new DepartmentDao();

		Department depart = new Department();
		depart.setDepartName("TEST");
		departmentDao.save(depart);
		
		Department depart1 = new Department();
		depart1.setDepartName("TEST");
		departmentDao.save(depart1);
		
		Department depart2 = new Department();
		depart2.setDepartName("TEST");
		departmentDao.save(depart2);
		
		Department depart3 = new Department();
		depart3.setDepartName("TEST");
		departmentDao.save(depart3);

		transaction.commit();
		System.out.println(session.isOpen());
	}
}
