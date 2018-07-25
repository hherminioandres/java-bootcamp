package com.globant.shopping;

/**
 * "Product" has a combination of a unique code, name, description and price, representing a real life product.
 * @author Herminio Andres Hernandez
 * @version 21/07/2018
 */

public class Product {
	
	private int code;
	private String name;
	private String description;
	private float value;
	/**
	 * Constructor of the Product class. Receive the necessary information by parameter to create the product.
	 * @param code Represents the product identifier.
	 * @param name Represents the product name.
	 * @param value Represents the products value.
	 */
	public Product(int code, String name, float value) {
		this.code = code;
		this.name = name;
		this.value = value;
		description = "Write description";
	}//Close constructor.
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
}
