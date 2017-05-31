package com.ultra.hibernate.model;

import java.util.List;
import java.util.Set;

import org.junit.Test;

public class A130HibernateStrategyTest extends A010HibernateTest {

	@Test
	public void testToOneFetch() {
		//fetch 取值为 join, 表示使用迫切左外连接的方式初始化School的属性,忽略 lazy属性.
		Student student = session.get(Student.class, 1);
		System.out.println(student.getClass());
		System.out.println(student.getStudentName());
		System.out.println(student.getSchool().getClass());
		System.out.println(student.getSchool().getSchoolName());
	}

	/**
	 * @Description: 多对一,一对一关系中many-to-one,ont-to-one标签的lazy属性
	 * 					false:不使用懒加载.proxy、no-proxy使用懒加载,
	 *
	 * @date 2017年5月8日,下午3:51:02
	 * @author fanbaoshen
	 * @version 5.0
	 *
	 */
	@Test
	public void testToOneLazy() {
		// 使用懒加载时,返回的对象只有主键属性(通过主键查询,传过去的主键赋值给返回的对象).
		Student student = session.load(Student.class, 1);
		// 代理对象
		System.out.println(student.getClass());
		System.out.println(student.getStudentName());
		// 代理对象
		System.out.println(student.getSchool().getClass());
	}

	/**
	 * @Description: 一对多,多对多关系中.hbm.xml文件set标签的fetch属性
	 * 					select:sql的where条件 students0_.SCHOOL_ID in (?, ?)
	 * 					subclass:sql的where条件 students0_.SCHOOL_ID in (select school0_.SCHOOL_ID from SCHOOLS school0_)
	 * 					join:????????????
	 *
	 * @date 2017年5月8日,下午3:16:29
	 * @author fanbaoshen
	 * @version 5.0
	 *
	 */

	@SuppressWarnings("unchecked")
	@Test
	public void testToManySubclassFetch() {
		List<School> schools = session.createQuery("From School").list();
		System.out.println(schools.size());
		for (School school : schools) {
			Set<Student> students = school.getStudents();
			if (students != null) {
				System.out.println(students.size());
				for (Student student : students) {
					System.out.println(student.getStudentName());
				}
			}
		}
	}
	@Test
	public void testToManyJoinFetch() {
		School school = session.get(School.class, 1);
		System.out.println(school.getSchoolName());
		System.out.println(school.getStudents().size());
	}

	/**
	 * @Description: 一对多,多对多关系中.hbm.xml文件set标签的batch-size属性,每次加载Student集合的个数,
	 *               即加载School对象的个数.
	 *
	 * @date 2017年5月8日,下午2:40:10
	 * @author fanbaoshen
	 * @version 5.0
	 *
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testToManyBatchSize() {
		List<School> schools = session.createQuery("From School").list();
		System.out.println(schools.size());
		for (School school : schools) {
			if (school.getStudents() != null) {
				System.out.println(school.getStudents().size());
			}
		}
	}

	/**
	 * 
	 * @Description: 一对多,多对多关系中.hbm.xml文件set标签的lazy属性
	 *               false:不使用懒加载,true使用懒加载,extra使用增强懒加载.
	 *
	 * @date 2017年5月8日,下午2:24:53
	 * @author fanbaoshen
	 * @version 5.0
	 *
	 */
	@Test
	public void testToManyLazy() {
		School school = session.get(School.class, 1);
		System.out.println(school.getSchoolName());
		System.out.println(school.getStudents().getClass());
		/**
		 * 增强懒加载
		 */
		// 使用增强懒加载时只会执行sql:select count(STUDENT_ID) from STUDENTS where
		// SCHOOL_ID =?
		// System.out.println(school.getStudents().size());
		// 使用增强懒加载时只会执行sql:select count(STUDENT_ID) from STUDENTS where
		// SCHOOL_ID =?
		// System.out.println(school.getStudents().isEmpty());
		// Student student = new Student();
		// student.setStudentId(1);
		// 使用增强懒加载时只会执行sql:select 1 from STUDENTS where SCHOOL_ID =? and
		// STUDENT_ID =?
		// System.out.println(school.getStudents().contains(student));
		/**
		 * 不使用懒加载
		 */

		// Set<Student> students = school.getStudents();
		// for (Student stu : students) {
		// if (stu != null) {
		// System.out.println(stu.getStudentName());
		// }
		// }

	}

	/**
	 * @Description: .hbm.xml(被加载对象的配置文件)中class标签的lazy属性 false:不使用懒加载,true使用懒加载.
	 * 
	 *
	 * @date 2017年5月8日,上午11:26:07
	 * @author fanbaoshen
	 * @version 5.0
	 *
	 */
	@Test
	public void testClassLevelLoad() {
		// 使用懒加载时,返回的代理对象只有主键属性(通过主键查询,传过去的主键赋值给返回的对象).
		// 不使用懒加载,返回的是实际对象.
		Module module = session.load(Module.class, 1);
		System.out.println(module.getClass());
		System.out.println(module.getModuleName());
		// 实际对象
		System.out.println(module.getNews().getClass());
		// System.out.println(module.getNews().getDesc());
	}
}
