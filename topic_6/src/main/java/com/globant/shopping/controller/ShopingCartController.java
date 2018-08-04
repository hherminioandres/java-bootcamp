package com.globant.shopping.controller;

import java.util.ArrayList;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.globant.shopping.Product;
import com.globant.shopping.ShoppingCart;

/**
 * This controller handle request input and generate response output of shopping cart
 * @author Herminio Andres Hernandez
 * @version 01-08-2018
 */
@Controller
@RequestMapping("/shoppingcart")
public class ShopingCartController {

	ShoppingCart shoppingCart = new ShoppingCart();
	
	@RequestMapping(path = "/calculate")
	@ResponseBody
	public float calculate() {
		return shoppingCart.calculate();
	}
	@RequestMapping(path = "/createProduct", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Product create(@RequestBody Product product) {
	    return shoppingCart.addProduct(product);
	}
	@RequestMapping(path = "/deleteProduct/{code}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("code") String code) {
    	shoppingCart.deleteProduct(code);
    }
    @RequestMapping(path = "/getProducts", method = RequestMethod.GET)
    @ResponseBody
	public ArrayList<Product> getProducts(){
		return shoppingCart.getProducts();
	}  
    @RequestMapping(path = "/getProduct/{code}", method = RequestMethod.GET)
    @ResponseBody
	public Product getProduct(@PathVariable("code") String code) {
		return shoppingCart.getProduct(code);
	}
    @RequestMapping(path = "/getQuantity", method = RequestMethod.GET)
    @ResponseBody
    public int getQuantity() {
    	return shoppingCart.productQuantity();
    }
}