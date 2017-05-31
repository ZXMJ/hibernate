package com.ultra.hibernate.model;

import org.junit.Test;

public class A120HibernateUnionSubclassTest extends A010HibernateTest {

	/**
	 * @Description: 优点:
	 * 					1. 无需使用辨别者列,子类独有的字段能添加非空约束.
	 * 
	 * 				缺点:
	 * 					1. 存在冗余的字段(两个表中共有字段)
	 * 					2. 若更新父表的字段, 则更新的效率较低	
	 *
	 * @date 2017年5月5日,下午3:02:40
	 * @author fanbaoshen
	 * @version 5.0
	 *
	 */
	@Test
	public void testUpdate(){
		String hql = "UPDATE Person p SET p.age = 20";
		session.createQuery(hql).executeUpdate();
	}
	
	
	/**
	 * @Description: 父类:从子父表联合结果中查询(父类对象可能在子类对象中).
	 * 				  子类:查询子表(子类对象不可能在父类对象中(装不下))
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
	 * 				  子类插入到子表中.
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
		
		Person personTemp = new Teacher();
		personTemp.setName("p-t-AA");
		personTemp.setAge(30);
		session.save(personTemp);
		
	}
}
