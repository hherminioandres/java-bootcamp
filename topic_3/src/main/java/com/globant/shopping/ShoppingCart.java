package com.globant.shopping;

import java.util.ArrayList;

/**
 * This class defines behavior of shopping cart. Allows to add and remove products in the cart and get its total value.
 * @author Herminio Andres Hernandez
 * @version 21/07/2018
 */
public class ShoppingCart {
	
	//Attributes of the class
	private ArrayList<Product> products = null;
	/**
	 * Constructor of shopping cart.
	 */
	public ShoppingCart() {
		products = new ArrayList<Product>();
	}//Close constructor
	/**
	 * The method adds a product to "Products" list.
	 * @param product Product to add.
	 */
	public void addProduct(Product product) {
		products.add(product);
	}
	/**
	 * The method removes a product from the "Products" list (it uses "productCode" to do it).
	 * @param productCode It's used for searching products on "Products" list. 
	 */
	public void deleteProduct(int productCode) {
		Product productAux = this.getProduct(productCode);
		if(productAux != null) {
			products.remove(productAux);
		}
	}
	/**
	 * This method returns all products on "Products" list.
	 * @return List containing all products.
	 */
	public ArrayList<Product> getProducts(){
		return products;
	}
	/**
	 * The method searches a product on "Products" list using its product code and returns it.
	 * @param productCode It's used for searching products on "Products" list. 
	 * @return Returns a product if it exists on "Products" list, else returns null.
	 */
	public Product getProduct(int productCode) {
		for(Product p: products) {
			if(p.getCode() == productCode) {
				return p;
			}
		}
		return null;
	}
	/**
	 * The method calculates "Products" list's total value.
	 * @return "Products" list's total value.
	 */
	public float calculate() {
		float sum = 0;
		for(Product p: products) {
			sum += p.getValue();
		}
		return sum;
	}
}
