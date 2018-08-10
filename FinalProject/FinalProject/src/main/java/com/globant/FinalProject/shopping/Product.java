package com.globant.FinalProject.shopping;

/**
 * "Product" has a combination of a unique code, name and price, representing a real life product.
 * @author Herminio Andres Hernandez
 * @version 21/07/2018 
 */
public class Product {
	
	private String idProduct;
	private String name;
	private String category;
	private float value;
	private int quantity=100;
	
	/**
	 * Constructor of the Product class. Receive the necessary information by parameter to create the product.
	 * @param code Represents the product identifier.
	 * @param name Represents the product name.
	 * @param value Represents the products value.
	 */
	public Product(String idProduct, String name, String category,float value) {
		this.idProduct = idProduct;
		this.name = name;
		this.category = category;
		this.value = value;
	}
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}

	public float getValue() {
		return value;
	}
	
	public void setValue(float value) {
		this.value = value;
	}
	
	public String getIdProduct() {
		return idProduct;
	}
	
	public void setIdProduct(String idProduct) {
		this.idProduct = idProduct;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}