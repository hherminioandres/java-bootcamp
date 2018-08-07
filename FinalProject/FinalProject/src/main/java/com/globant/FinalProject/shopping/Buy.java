package com.globant.FinalProject.shopping;

/**
 * This class represents a buy that is aggregate into the shopping cart.
 * Just contain idProduct and quantity to buy.
 * @author Herminio Andres Hernandez
 * @version 05/08/2018
 */
public class Buy {

	private String idProduct;
	private int quantity;
	
	public Buy(String idProduct, int quantity) {
		this.idProduct = idProduct;
		this.quantity = quantity;
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
