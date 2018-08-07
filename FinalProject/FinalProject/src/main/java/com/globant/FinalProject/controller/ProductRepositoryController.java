package com.globant.FinalProject.controller;

import java.sql.SQLException;
import java.util.List;
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
 * This controller handles requests for the product repository.
 * @author Herminio Andres Hernandez
 * @version 04/08/2018
 */
@Controller
@RequestMapping("/api/products")
public class ProductRepositoryController {

	static ProductRepository productRepository = null;
	
	public ProductRepositoryController() throws SQLException {
		productRepository = getRepository();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Product createUser(@RequestBody Product product) throws SQLException {
		return productRepository.add(product);
	}
	
	@RequestMapping(value = "/{idProduct}", method = RequestMethod.GET)
	@ResponseBody
	public Product findUser(@PathVariable("idProduct") String idProduct) throws SQLException {
		return productRepository.find(idProduct);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Product> getProducts() throws SQLException {
		return productRepository.getProducts();
	}
	
	@RequestMapping(path = "/category/{category}", method = RequestMethod.GET)
	@ResponseBody
	public List<Product> findCategory(@PathVariable("category") String category) throws SQLException{
		return productRepository.findByCategory(category);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Product updateUser(@RequestBody Product product) throws SQLException {
		return productRepository.update(product);
	}
	
	@RequestMapping(value = "/{idProduct}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void deleteUser(@PathVariable("idProduct") String idProduct) throws SQLException {
		productRepository.delete(idProduct);
	}
	
	public static ProductRepository getRepository() throws SQLException{
		if(productRepository == null) {
			productRepository = new ProductRepository();
		}
		return productRepository;
	}
}
