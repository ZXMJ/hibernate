package com.ultra.hibernate.model;

import java.util.Date;

import org.junit.Test;

public class A030HibernateComponentTest extends A010HibernateTest{


	/**
	 * TODO 组成关系
	 */

	/**
	 * @Description: 映射组成关系测试
	 *
	 * @date 2017年5月3日,下午2:08:50
	 * @author fanbaoshen
	 * @version 5.0
	 *
	 */
	@Test
	public void testComponent() {
		Detail detail = new Detail();
		detail.setSize(1024);
		detail.setName("AAA");
		News news = new News();
		news.setTitle("AA");
		news.setAuthor("aa");
		news.setDate(new Date());
		news.setDetail(detail);

		session.save(news);
	}

}
