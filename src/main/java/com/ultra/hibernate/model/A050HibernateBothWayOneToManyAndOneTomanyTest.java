package com.ultra.hibernate.model;

import org.junit.Test;

public class A050HibernateBothWayOneToManyAndOneTomanyTest extends A010HibernateTest {

	
	@Test
	public void testBothWayOneToManyAndOneTomanyDelete() {
		//在不设定级联关系的情况下, 被外键关联的对象, 不能直接删除
		//级联设置(了解):.hbm.xml配置文件set标签cascade属性设置为delete,删除School时与之关联的所有Student也被删除.
		School school = session.get(School.class, 1);
		session.delete(school); 
		
	}
	
	@Test
	public void testBothWayOneToManyAndOneTomanyUpdate() {
		
	}
	
	@Test
	public void testBothWayOneToManyAndOneTomanyGet() {
		School school = session.get(School.class, 1);
		System.out.println(school.getSchoolName());
		// 返回的Student的集合是 Hibernate 内置的集合类型,该类型具有延迟加载和存放代理对象的功能. 
		System.out.println(school.getStudents().getClass());
		
//		Student student = session.get(Student.class, 1);
//		System.out.println(student.getStudentName());
//		// 代理对象
//		System.out.println(student.getSchool().getClass());
	}
	
	/**
	 * @Description: 双向的一对多,多对一--添加
	 *				 先添加School后添加Student:三条insert,两条update
	 * 				 先添加Student后添加School:三条insert,四条update
	 * 				 设置.hbm.xml配置文件set标签的inverse为true时,先添加School后添加Student,只执行三条insert
	 * @date 2017年5月4日,上午9:47:18
	 * @author fanbaoshen
	 * @version 5.0
	 *
	 */
	@Test
	public void testBothWayOneToManyAndOneTomanySave() {
		School school = new School();
		school.setSchoolName("school--AAA");
		
		Student student1 = new Student();
		Student student2 = new Student();
		student1.setStudentName("student--AAA");
		student2.setStudentName("student--BBB");
		
		school.getStudents().add(student1);
		school.getStudents().add(student2);

		student1.setSchool(school);
		student2.setSchool(school);

		session.save(school);
		
		session.save(student1);
		session.save(student2);
		
//		session.save(school);
	}

}
