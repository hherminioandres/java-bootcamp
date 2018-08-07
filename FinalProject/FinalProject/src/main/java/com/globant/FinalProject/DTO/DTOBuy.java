package com.globant.FinalProject.DTO;

/**
 * This class represents a buy that is aggregate into the shopping cart.
 * @author Herminio Andres Hernandez
 * @version 05/08/2018
 */
public class DTOBuy {

	private String idProduct;
	private int quantity;
	
	public DTOBuy(String idProduct, int quantity) {
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
