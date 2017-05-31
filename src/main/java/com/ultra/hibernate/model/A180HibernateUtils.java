package com.ultra.hibernate.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class A180HibernateUtils {

	private static A180HibernateUtils a180HibernateUtils = new A180HibernateUtils();
	private Session session;
	private SessionFactory sessionFactory = null;

	private A180HibernateUtils() {
	}

	public static A180HibernateUtils getInstance() {
		return a180HibernateUtils;
	}

	public SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
					.configure("/hibernate.cfg.xml").build();
			Metadata metadata = new MetadataSources(standardRegistry).getMetadataBuilder()
					.applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE).build();
			sessionFactory = metadata.getSessionFactoryBuilder().applyBeanManager(metadata).build();
		}
		return sessionFactory;
	}
	
	public Session getSession() {
		return getSessionFactory().getCurrentSession();
	}
}
