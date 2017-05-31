package com.ultra.hibernate.model;

import org.junit.Test;

public class A090HibernateBothWayManyToManyTest extends A010HibernateTest{

	
	@Test
	public void testGet(){
		Product2 product = session.get(Product2.class, 1);
		System.out.println(product.getProductName());
		// hibernate内置对象:org.hibernate.collection.internal.PersistentSet
		System.out.println(product.getItems().getClass());
		System.out.println(product.getItems().size());
	}
	
	
	
	@Test
	public void testSave(){
		Item2 item1 = new Item2();
		item1.setItemName("I-AA");
		
		Item2 item2 = new Item2();
		item2.setItemName("I-BB");
		
		Product2 product1 = new Product2();
		product1.setProductName("P--AA");
		
		Product2 product2 = new Product2();
		product2.setProductName("P--BB");
		
		item1.getProducts().add(product1);
		item1.getProducts().add(product2);
		
		item2.getProducts().add(product1);
		item2.getProducts().add(product2);
		
		product1.getItems().add(item1);
		product1.getItems().add(item2);
		
		product2.getItems().add(item1);
		product2.getItems().add(item2);
		
		session.save(item1);
		session.save(item2);
		
		session.save(product1);
		session.save(product2);
	}
}
