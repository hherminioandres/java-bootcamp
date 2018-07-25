package com.globant.shopping;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class ShoppingCartTest {
	
	@Test
	public void testPutProductOnCart() {
		ShoppingCart cart = new ShoppingCart();
		Product product = mock(Product.class);
		cart.addProduct(product);	
		assertEquals(cart.getProducts().size(), 1);
	}
	@Test
	public void testCalculateSumProducts() {
		ShoppingCart cart = new ShoppingCart();
		Product product = mock(Product.class);
		when(product.getValue()).thenReturn(10f);
		cart.addProduct(product);
		cart.addProduct(product);
		cart.addProduct(product);
		assertEquals(cart.calculate(), 30f);
	}
	@Test
	public void testGetProductByCodeFromCart() {
		ShoppingCart cart = new ShoppingCart();
		Product prod1 = mock(Product.class);
		when(prod1.getCode()).thenReturn(0110);
		when(prod1.getValue()).thenReturn(10f);
		cart.addProduct(prod1);
		assertEquals(cart.getProduct(0110).getValue(), 10f);
	}
	@Test
	public void testDeleteProductFromCart() {
		ShoppingCart cart = new ShoppingCart();
		Product prod1 = mock(Product.class);
		when(prod1.getCode()).thenReturn(0110);
		cart.addProduct(prod1);
		cart.deleteProduct(0110);
		assertTrue(cart.getProducts().isEmpty());
	}
}
