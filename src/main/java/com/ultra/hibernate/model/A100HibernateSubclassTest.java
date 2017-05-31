package com.ultra.hibernate.model;

import org.junit.Test;

public class A100HibernateSubclassTest extends A010HibernateTest {

	/**
	 * @Description: subclass的缺点
	 * 					1). 子类中字段不能添加非空约束.
	 * 					2). 表中多了一条辨别者列.
	 * 					3). 继承层次较深时,字段较多.
	 *
	 * @date 2017年5月5日,下午3:02:40
	 * @author fanbaoshen
	 * @version 5.0
	 *
	 */
	/**
	 * @Description: 子父类查询都执行一条select语句.
	 *
	 * @date 2017年5月5日,下午3:07:16
	 * @author fanbaoshen
	 * @version 5.0
	 *
	 */
	@Test
	public void testGet(){
		Person person = session.get(Person.class, 1);
		System.out.println(person);
		Teacher teacher = session.get(Teacher.class, 2);
		System.out.println(teacher);
	}
	
	/**
	 * @Description: 对于子父类插入同一张表中,辨别者列hibernate自动维护.
	 *
	 * @date 2017年5月5日,下午3:06:05
	 * @author fanbaoshen
	 * @version 5.0
	 *
	 */
	@Test
	public void testSave(){
		Person person = new Person();
		person.setName("p--AA");
		person.setAge(20);
		
		Teacher teacher = new Teacher();
		teacher.setName("t--AA");
		teacher.setAge(25);
		teacher.setSubject("CN");
		
		session.save(person);
		session.save(teacher);
		
	}
}
