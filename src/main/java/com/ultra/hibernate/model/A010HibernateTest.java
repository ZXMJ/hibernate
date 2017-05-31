package com.ultra.hibernate.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;


public class A010HibernateTest {

	/**
	 * TODO
	 * 对象的状态:
	 * 			瞬时态:未与session实例关联.在数据库中没有与之关联的记录.
	 * 			脱管态:未与session实例关联,在数据库中有与之关联的记录.
	 * 			持久态:与session实例关联,在数据库中有与之关联的记录.
	 * 		处于持久态或者脱管态的对象主键属性不能被修改.
	 */
	
	protected SessionFactory sessionFactory;
	protected Session session;
	protected Transaction transaction;

	@Before
	public void init() {
		StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure("/hibernate.cfg.xml")
				.build();
		Metadata metadata = new MetadataSources(standardRegistry).getMetadataBuilder()
				.applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE).build();
		sessionFactory = metadata.getSessionFactoryBuilder().applyBeanManager(metadata).build();
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
	}
	
	@After
	public void destory() {
		transaction.commit();
		session.close();
		sessionFactory.close();
	}

}
