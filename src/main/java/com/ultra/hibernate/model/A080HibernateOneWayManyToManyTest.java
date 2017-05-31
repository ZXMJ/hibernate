package com.ultra.hibernate.model;

import org.junit.Test;

public class A080HibernateOneWayManyToManyTest extends A010HibernateTest {

	@Test
	public void testGet() {
		Product product = session.get(Product.class, 1);
		System.out.println(product.getProductName());
		System.out.println(product.getItems().getClass());
		System.out.println(product.getItems().size());
	}

	@Test
	public void testSave() {
		Item item1 = new Item();
		item1.setItemName("I-AA");

		Item item2 = new Item();
		item2.setItemName("I-BB");

		Product product1 = new Product();
		product1.setProductName("P--AA");

		Product product2 = new Product();
		product2.setProductName("P--BB");

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
