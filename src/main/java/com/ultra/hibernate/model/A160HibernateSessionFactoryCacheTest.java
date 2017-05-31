package com.ultra.hibernate.model;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.query.Query;
import org.junit.Test;

public class A160HibernateSessionFactoryCacheTest extends A010HibernateTest {

	/**
	 * @Description: 设置Criteria接口可以从缓存中获取.
	 *
	 * @date 2017年5月10日,下午4:35:44
	 * @author fanbaoshen
	 * @version 5.0
	 *
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testCriteriaSecondLevelCache() {
		Criteria criteria = session.createCriteria(Emp.class);
		// 设置从缓存中查询
		criteria.setCacheable(true);
		// 调用两次,测试缓存
		System.out.println(criteria.list().size());
		System.out.println(criteria.list().size());

	}

	/**
	 * @Description: 设置Query接口可以从缓存中获取.
	 *
	 * @date 2017年5月10日,下午4:35:09
	 * @author fanbaoshen
	 * @version 5.0
	 *
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testQuerySecondLevelCache() {
		String hql = "FROM Emp";
		Query<Emp> query = session.createQuery(hql);
		// 设置从缓存中查询
		query.setCacheable(true);
		// 调用两次,测试缓存
		System.out.println(query.list().size());
		System.out.println(query.list().size());

		// String hql2 = "FROM Emp";
		// Query<Emp> query2 = session.createQuery(hql2);
		// query2.setCacheable(true);
		// System.out.println(query2.list().size());
	}

	/**
	 * @Description: 集合的二级缓存
	 *
	 * @date 2017年5月10日,下午4:20:24
	 * @author fanbaoshen
	 * @version 5.0
	 *
	 */
	@Test
	public void testCollectionSecondLevelCache() {
		Department department = session.get(Department.class, 10);
		System.out.println(department);
		// 显式初始化对象的集合对象
		Hibernate.initialize(department.getEmps());

		System.out.println(department.getEmps());

		// transaction.commit();
		// session.close();
		//
		// session = sessionFactory.openSession();
		// transaction = session.beginTransaction();
		//
		// Department department2 = session.get(Department.class, 10);
		// System.out.println(department2);
		// System.out.println(department2.getEmps());
	}

	/**
	 * @Description: 对象的二级缓存
	 *
	 * @date 2017年5月10日,下午4:20:29
	 * @author fanbaoshen
	 * @version 5.0
	 *
	 */
	@Test
	public void testSecondLevelCache() {
		Emp emp = session.get(Emp.class, 7369);
		System.out.println(emp);

		transaction.commit();
		session.close();

		session = sessionFactory.openSession();
		transaction = session.beginTransaction();

		Emp emp2 = session.get(Emp.class, 7369);
		System.out.println(emp2);
	}
}
