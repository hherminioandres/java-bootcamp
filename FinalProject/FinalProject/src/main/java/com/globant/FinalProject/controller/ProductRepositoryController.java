package com.globant.FinalProject.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.globant.FinalProject.shopping.Product;
import com.globant.FinalProject.shopping.ProductRepository;

/**
 * This controller handle requests and responses of products repository.
 * The methods of this class allow add, delete, update and find products
 * per different variables: Product and category.
 * @author Herminio Andres Hernandez
 * @version 04/08/2018
 */
@Controller
@RequestMapping("/")
public class ProductRepositoryController {

	static ProductRepository productRepository = null;
	
	public ProductRepositoryController() throws SQLException {
		productRepository = getRepository();
	}
	
	@RequestMapping(value = "/products", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Product createUser(@RequestBody Product product) throws SQLException {
		return productRepository.addProduct(product);
	}
	
	@RequestMapping(value = "/products/{idProduct}", method = RequestMethod.GET)
	@ResponseBody
	public Product findUser(@PathVariable("idProduct") String idProduct) throws SQLException {
		return productRepository.findProduct(idProduct);
	}
	
	@RequestMapping(value = "/products", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<Product> getProducts() throws SQLException {
		return productRepository.getProducts();
	}
	
	@RequestMapping(path = "/products/category/{category}", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<Product> findCategory(@PathVariable("category") String category) throws SQLException{
		return productRepository.findByCategory(category);
	}
	
	@RequestMapping(value = "/products", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Product updateUser(@RequestBody Product product) throws SQLException {
		return productRepository.updateProduct(product);
	}
	
	@RequestMapping(value = "/products/{idProduct}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void deleteUser(@PathVariable("idProduct") String idProduct) throws SQLException {
		productRepository.deleteProduct(idProduct);
	}
	
	public static ProductRepository getRepository() throws SQLException{
		if(productRepository == null) {
			productRepository = new ProductRepository();
		}
		return productRepository;
	}
}
