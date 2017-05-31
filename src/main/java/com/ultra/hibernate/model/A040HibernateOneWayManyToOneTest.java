package com.ultra.hibernate.model;

import java.util.Date;

import org.junit.Test;


public class A040HibernateOneWayManyToOneTest extends A010HibernateTest{
	
	
	/**
	 * TODO
	 * 对应关系
	 */
	
	/**
	 * @Description: 单向多对一对应关系--删除
	 * 				  在不设定级联关系的情况下,被关联的外键对象不能被删除.
	 *
	 * @date 2017年5月3日,下午2:55:11
	 * @author fanbaoshen
	 * @version 5.0
	 *
	 */
	@Test
	public void testManyToOneDelete(){
		Module module = session.get(Module.class, 1);
		session.delete(module);
//		session.delete(module.getNews());
	}
	/**
	 * @Description: 单向多对一对应关系--修改
	 *
	 * @date 2017年5月3日,下午2:55:11
	 * @author fanbaoshen
	 * @version 5.0
	 *
	 */
	@Test
	public void testManyToOneUpdate(){
		Module module = session.get(Module.class, 1);
		module.getNews().setTitle("FF");
	}
	/**
	 * @Description: 单向多对一对应关系--查询:外键对象是懒加载,使用的代理对象.使用外键对象时如果session已关闭则会抛异常LazyInitializationException

	 *
	 * @date 2017年5月3日,下午2:55:11
	 * @author fanbaoshen
	 * @version 5.0
	 *
	 */
	@Test
	public void testManyToOneGet(){
		Module module = session.get(Module.class, 1);
		System.out.println(module);
		System.out.println(module.getNews().getClass());
		System.out.println(module.getNews());
		
	}
	/**
	 * @Description: 单向多对一对应关系--添加
	 * 				 无外键的对象先save,有外键的对象后save,执行三条insert.
	 * 				 有外键的对象先save,无外键的对象后save,执行三条insert,和两条update.
	 *
	 * @date 2017年5月3日,下午2:55:11
	 * @author fanbaoshen
	 * @version 5.0
	 *
	 */
	@Test
	public void testManyToOneSave(){
		Detail detail = new Detail(1024,"AAA");
		News news = new News("AA", "aa", new Date(), detail);

		Module module1 = new Module("Aa", "ball", news);
		Module module2 = new Module("Bb", "song", news);
		
		session.save(news);
		
		session.save(module2);
		session.save(module1);
		
//		session.save(news);
	}
	

}
