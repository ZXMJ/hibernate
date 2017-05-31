package com.ultra.hibernate.model;

import org.junit.Test;

public class A060HibernateForeignOnoToOneTest extends A010HibernateTest {

	/**
	 * @Description: 查询无外键的对象
	 *
	 * @date 2017年5月5日,上午9:50:02
	 * @author fanbaoshen
	 * @version 5.0
	 *
	 */
	@Test
	public void testGet2() {
		Manager manager = session.get(Manager.class, 1);
		System.out.println(manager.getMangerName());
		// 实际对象,使用左外连接直接查询出两个对象.
		// 因为manage没有外键属性,不能根据外键去查询depart.
		System.out.println(manager.getDepart().getClass());
		System.out.println(manager.getDepart().getDepartName());

	}

	/**
	 * @Description: 查询有外键的对象
	 *
	 * @date 2017年5月5日,上午9:49:27
	 * @author fanbaoshen
	 * @version 5.0
	 *
	 */
	@Test
	public void testGet() {
		Depart depart = session.get(Depart.class, 1);
		System.out.println(depart.getDepartName());
		// 使用代理对象,懒加载
		System.out.println(depart.getManager().getClass());
		// 通过depart获取manager信息时,会使用左外连接查询,on的默认条件是manager的主键等于depart的主键
		// 在Manager.hbm.xml配置文件中,one-to-one标签的property-ref属性值设置为Depart对象中属性Manager的属性名.
		System.out.println(depart.getManager().getMangerName());
	}

	/**
	 * @Description: 添加时先添加无外键的对象,再添加有外键的对象,减少执行是sql的执行
	 *
	 * @date 2017年5月5日,上午9:32:25
	 * @author fanbaoshen
	 * @version 5.0
	 *
	 */
	@Test
	public void testSave() {
		Manager manager = new Manager();
		manager.setMangerName("manager--AA");

		Depart depart = new Depart();
		depart.setDepartName("depart--AA");

		manager.setDepart(depart);

		depart.setManager(manager);

		session.save(manager);
		session.save(depart);
	}
}
