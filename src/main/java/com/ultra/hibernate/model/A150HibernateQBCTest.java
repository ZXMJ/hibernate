package com.ultra.hibernate.model;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.junit.Test;

public class A150HibernateQBCTest extends A010HibernateTest {

	/**
	 * @Description: Query执行增删改查操作(基于对象的操作). 添加时只支持 insert into ... select ...
	 *               形式的添加, 不支持 insert into ... values ... 形式的添加.
	 *
	 * @date 2017年5月10日,下午2:55:34
	 * @author fanbaoshen
	 * @version 5.0
	 *
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testQueryExecuteUpdate() {
		// 不能执行添加 String hql = "INSERT INTO Department (departName) VALUES (?)";
		// String hql = "DELETE FROM Department d WHERE d.departmentId = ?";
		String hql = "UPDATE Department d SET d.departName = ? WHERE d.departmentId = ?";
		Query<Department> query = session.createQuery(hql);

		// query.setParameter(0, "TEST");
		// query.setParameter(0, 42);
		query.setParameter(0, "OPERATIONS");
		query.setParameter(1, 40);

		query.executeUpdate();
	}

	/**
	 * @Description: 本地sql执行增删改(基于表的操作)
	 *
	 * @date 2017年5月10日,下午2:35:58
	 * @author fanbaoshen
	 * @version 5.0
	 *
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Test
	public void testNativeSql() {
		String sql = "INSERT INTO DEPARTMENT (DEPART_NAME) VALUES (?)";
		// String sql = "DELETE FROM DEPARTMENT WHERE DEPARTMENT_ID = ?";
		// String sql = "UPDATE DEPARTMENT SET DEPART_NAME = ? WHERE
		// DEPARTMENT_ID = ?";
		NativeQuery<Department> nativeQuery = session.createSQLQuery(sql);
		nativeQuery.setParameter(0, "TEST");
		// nativeQuery.setParameter(0, 41);
		// nativeQuery.setParameter(0, "MYTEST");
		// nativeQuery.setParameter(1, 42);
		nativeQuery.executeUpdate();
	}

	/**
	 * @Description: 排序
	 *
	 * @date 2017年5月10日,下午2:00:07
	 * @author fanbaoshen
	 * @version 5.0
	 *
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Test
	public void testOrder() {
		Criteria criteria = session.createCriteria(Emp.class);
		// 排序
		criteria.addOrder(Order.asc("empName"));
		criteria.addOrder(Order.desc("sal"));

		// 分页
		int pageNo = 1;
		int pageSize = 2;
		criteria.setFirstResult((pageNo - 1) * pageSize).setMaxResults(pageSize);

		List<Emp> emps = criteria.list();
		for (Emp emp : emps) {
			System.out.println(emp);
		}
	}

	/**
	 * @Description: 使用统计函数
	 *
	 * @date 2017年5月10日,下午1:52:21
	 * @author fanbaoshen
	 * @version 5.0
	 *
	 */
	@SuppressWarnings({ "deprecation" })
	@Test
	public void testMax() {
		Criteria criteria = session.createCriteria(Emp.class);
		//
		criteria.setProjection(Projections.max("sal"));

		System.out.println(criteria.uniqueResult());

	}

	/**
	 * @Description: 条件中带有and、or
	 *
	 * @date 2017年5月10日,下午1:43:19
	 * @author fanbaoshen
	 * @version 5.0
	 *
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Test
	public void testAndOrQBC() {
		Criteria criteria = session.createCriteria(Emp.class);
		// and 构造类
		Conjunction conjunction = Restrictions.conjunction();
		Department depart = new Department();
		depart.setDepartmentId(10);
		conjunction.add(Restrictions.eq("department", depart));
		conjunction.add(Restrictions.gt("sal", 500f));

		// or 构造类
		Disjunction disjunction = Restrictions.disjunction();
		// ilike()不区分大小写.
		disjunction.add(Restrictions.ilike("empName", "%a%"));
		// like()区分大小写.(mysql默认不区分大小写,所以两个方法对mysql的结果是一样的)
		disjunction.add(Restrictions.like("empName", "N", MatchMode.ANYWHERE));

		criteria.add(conjunction);
		criteria.add(disjunction);

		List<Emp> emps = criteria.list();
		for (Emp emp : emps) {
			System.out.println(emp);
		}
	}

	/**
	 * @Description: QBC基本查询
	 *
	 * @date 2017年5月10日,下午1:16:58
	 * @author fanbaoshen
	 * @version 5.0
	 *
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Test
	public void testBasicQBC() {
		// 创建Emp的查询器
		Criteria criteria = session.createCriteria(Emp.class);
		// department属性等于该部门
		Department department = new Department();
		department.setDepartmentId(10);
		criteria.add(Restrictions.eq("department", department));
		// 工资大于等于500
		criteria.add(Restrictions.ge("sal", 500F));
		// 工资小于等于5000
		criteria.add(Restrictions.le("sal", 5000F));
		// empId不等于7934
		criteria.add(Restrictions.ne("empId", 7934));

		List<Emp> emps = criteria.list();

		System.out.println(emps.size());

		System.out.println(emps);
	}
}
