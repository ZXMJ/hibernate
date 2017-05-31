package com.ultra.hibernate.model;

import java.sql.Connection;
import java.sql.SQLException;

import org.hibernate.jdbc.Work;
import org.junit.Test;

public class A190HibernatBatchTest extends A010HibernateTest{

	/**
	 * @Description: 原生jdbc的Connection批量操作最快
	 *
	 * @date 2017年5月10日,下午5:52:00
	 * @author fanbaoshen
	 * @version 5.0
	 *
	 */
	@Test
	public void testBatch(){
		session.doWork(new Work() {
			@Override
			public void execute(Connection connection) throws SQLException {
				
			}
		});
		
	}
}
