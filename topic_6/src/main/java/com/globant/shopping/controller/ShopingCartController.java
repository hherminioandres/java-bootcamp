package com.globant.shopping.controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;
import com.globant.shopping.Product;
import com.globant.shopping.ShoppingCart;

/**
 * This controller handle request input and generate response output of shopping cart
 * @author Herminio Andres Hernandez
 * @version 01-08-2018
 */
@RestController
@RequestMapping("/shoppingcart")
public class ShopingCartController {

	ShoppingCart shoppingCart = new ShoppingCart();
	
	@RequestMapping("/calculate")
	public float calculate() {
		return shoppingCart.calculate();
	}
    @RequestMapping("/addProduct")
    public boolean addProduct(@RequestParam(value="code") String code, @RequestParam(value="name") String name, @RequestParam(value="value")float value) {
    	return shoppingCart.addProduct(new Product(code, name, value));
    }
    @RequestMapping("/deleteProduct")
    public void deleteProduct(@RequestParam(value="code") String code) {
    	shoppingCart.deleteProduct(code);
    }
    @RequestMapping("/getProducts")
	public ArrayList<Product> getProducts(){
		return shoppingCart.getProducts();
	}
    @RequestMapping("/getProduct")
	public Product getProduct(@RequestParam(value="code") String code) {
		return shoppingCart.getProduct(code);
	}
}
