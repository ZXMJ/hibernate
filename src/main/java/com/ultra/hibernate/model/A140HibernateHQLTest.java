package com.ultra.hibernate.model;

import java.util.Arrays;
import java.util.List;

import org.hibernate.query.Query;
import org.junit.Test;

public class A140HibernateHQLTest extends A010HibernateTest {

	/**
	 * @Description: TODO
	 *
	 * @date 2017年5月10日,上午11:08:29
	 * @author fanbaoshen
	 * @version 5.0
	 *
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testInnerJoinFetch() {
		String hql = "SELECT d FROM Department d INNER JOIN FETCH d.emps";
		Query<Department> query = session.createQuery(hql);
		List<Department> departments = query.getResultList();
		System.out.println(departments.size());
		for (Department department : departments) {
			System.out.println(department);
			System.out.println(department.getEmps().size());
		}
	}

	/**
	 * @Description: TODO
	 *
	 * @date 2017年5月10日,上午11:08:25
	 * @author fanbaoshen
	 * @version 5.0
	 *
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testInnerJoin() {
		String hql = "SELECT d FROM Department d INNER JOIN d.emps";
		Query<Department> query = session.createQuery(hql);
		List<Department> departments = query.getResultList();
		System.out.println(departments.size());
		for (Department department : departments) {
			System.out.println(department);
			System.out.println(department.getEmps().size());
		}
	}

	/**
	 * @Description: LeftJoin:查询时不初始化集合对象.
	 *
	 * @date 2017年5月10日,上午11:01:12
	 * @author fanbaoshen
	 * @version 5.0
	 *
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testLeftJoin() {
		String hql = "SELECT DISTINCT d FROM Department d LEFT JOIN d.emps";
		Query<Department> query = session.createQuery(hql);
		List<Department> departments = query.getResultList();
		System.out.println(departments.size());
		for (Department department : departments) {
			System.out.println(department.getEmps().size());
		}
	}

	/**
	 * @Description: LeftJoinFetch:查询时初始化集合对象
	 *
	 * @date 2017年5月10日,上午11:00:31
	 * @author fanbaoshen
	 * @version 5.0
	 *
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testLeftJoinFetch() {
		String hql = "SELECT DISTINCT d FROM Department d LEFT JOIN FETCH d.emps";
		Query<Department> query = session.createQuery(hql);
		List<Department> departments = query.getResultList();
		System.out.println(departments.size());
		for (Department department : departments) {
			System.out.println(department.getEmps().size());
		}
	}

	/**
	 * @Description: 分组查询
	 *
	 * @date 2017年5月9日,下午2:47:57
	 * @author fanbaoshen
	 * @version 5.0
	 *
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testGroupBy() {
		String hql = "SELECT min(e.sal),max(e.sal) FROM Emp e GROUP BY e.department HAVING min(e.sal) > ?";
		Query<Object[]> query = session.createQuery(hql);
		query.setParameter(0, 800F);
		List<Object[]> objs = query.getResultList();
		for (Object[] obj : objs) {
			System.out.println(Arrays.asList(obj));
		}
	}

	/**
	 * @Description: 查询部分属性,将查询结果封装成一个对象, 注意:
	 *               1).需要提供查询属性的构造函数,以便查询后的结果可以直接创建成对象, 构造函数参数的顺序和查询结果的顺序一致.
	 *               2). 提供无参构造函数.
	 *
	 * @date 2017年5月9日,下午2:36:55
	 * @author fanbaoshen
	 * @version 5.0
	 *
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testGetProperties2() {
		String hql = "SELECT new Emp(e.empId,e.empName) FROM Emp e where e.department = ?";
		Query<Emp> query = session.createQuery(hql);
		Department depart = new Department();
		depart.setDepartmentId(10);
		query.setParameter(0, depart);
		List<Emp> emps = query.getResultList();
		for (Emp emp : emps) {
			System.out.println(emp);
		}
	}

	/**
	 * @Description: 原生态查询部分属性,返回Object[]的集合.
	 *
	 * @date 2017年5月9日,下午2:33:53
	 * @author fanbaoshen
	 * @version 5.0
	 *
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testGetProperties() {
		String hql = "SELECT e.empName,e.sal FROM Emp e where e.department = ?";
		Query<Object[]> query = session.createQuery(hql);
		Department depart = new Department();
		depart.setDepartmentId(10);
		query.setParameter(0, depart);
		List<Object[]> objs = query.getResultList();
		for (Object[] obj : objs) {
			System.out.println(Arrays.asList(obj));
		}
	}

	/**
	 * @Description: NamedQuery:在.hbm.xml中配置query标签(参数可以是对象,这里的Department作为参数)
	 *
	 * @date 2017年5月9日,下午2:13:13
	 * @author fanbaoshen
	 * @version 5.0
	 *
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testNamedQuery() {
		Query<Emp> query = session.getNamedQuery("getEmpByDeptId");
		Department depart = new Department();
		depart.setDepartmentId(10);
		query.setParameter(0, 200F).setParameter(1, 5000F).setParameter(2, depart);
		List<Emp> emps = query.getResultList();
		System.out.println(emps.size());

	}

	/**
	 * @Description: 分页查询
	 *
	 * @date 2017年5月9日,下午1:59:47
	 * @author fanbaoshen
	 * @version 5.0
	 *
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testPage() {
		String hql = "FROM Emp e ORDER BY e.empId";
		Query<Emp> query = session.createQuery(hql);
		int pageNo = 3;
		int pageSize = 5;
		query.setFirstResult((pageNo - 1) * pageSize).setMaxResults(pageSize);
		List<Emp> emps = query.getResultList();
		for (Emp emp : emps) {
			System.out.println(emp);
		}
	}

	/**
	 * @Description: 基于命名参数的HQL.
	 *
	 * @date 2017年5月9日,下午1:49:22
	 * @author fanbaoshen
	 * @version 5.0
	 *
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testNamedParameterHql() {
		String hql = "FROM Emp e WHERE e.sal > :minSal AND e.sal < :maxSal";
		Query<Emp> query = session.createQuery(hql);
		List<Emp> emps = query.setParameter("minSal", 200F).setParameter("maxSal", 5000F).list();
		System.out.println(emps.size());
	}

	/**
	 * @Description: 基于位置的参数的HQL,获取集合的结果
	 *
	 * @date 2017年5月9日,下午1:32:18
	 * @author fanbaoshen
	 * @version 5.0
	 *
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testHqlListResult() {
		String hql = "FROM Emp e WHERE e.sal > ? AND e.sal < ?";
		Query<Emp> query = session.createQuery(hql);
		// 绑定参数
		query.setParameter(0, 200F).setParameter(1, 5000F);
		// 获取list的集合
		List<Emp> emps1 = query.getResultList();
		List<Emp> emps2 = query.list();
		System.out.println(emps1.size());
		System.out.println(emps2.size());
	}

	/**
	 * @Description: 获取单个的结果
	 *
	 * @date 2017年5月9日,下午1:49:39
	 * @author fanbaoshen
	 * @version 5.0
	 *
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testHqlSingleResult() {
		String hql = "FROM Emp e WHERE e.empId = ?";
		Query<Emp> query = session.createQuery(hql);
		// 绑定参数
		query.setParameter(0, 7521);
		// 获取一个对象(查询结果不唯一会抛异常NonUniqueResultException)
		// 下面两种是一样的.
		Emp emp = query.getSingleResult();
		Emp emp2 = query.uniqueResult();
		System.out.println(emp);
		System.out.println(emp2);
	}
}
