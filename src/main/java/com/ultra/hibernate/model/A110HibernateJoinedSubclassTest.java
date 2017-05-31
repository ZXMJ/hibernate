package com.ultra.hibernate.model;

import org.junit.Test;

public class A110HibernateJoinedSubclassTest extends A010HibernateTest {

	/**
	 * @Description: 	
	 * JoinedSubclass优点:
	 *			1. 不需要使用了辨别者列.
	 *			2. 子类独有的字段能添加非空约束.
	 *			3. 没有冗余的字段. 
	 *
	 * @date 2017年5月5日,下午3:02:40
	 * @author fanbaoshen
	 * @version 5.0
	 *
	 */
	/**
	 * @Description: 父类:左外连接子表.
	 * 				  子类:两表内连接
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
	 * @Description: 父类插入到父表.
	 * 				  子类插入到子父两个表中.
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
