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
	
	public ProductRepositoryController() {
		productRepository = getRepository();
	}
	/**
	 * This method handles request and response to "add" method of products repository. 
	 * @param product 
	 * @return created product. 
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Product createProduct(@RequestBody Product product) {
		return productRepository.add(product);
	}
	/**
	 *  The method handles request and response to "find" method of products repository. 
	 * @param idProduct
	 * @return
	 */
	@RequestMapping(value = "/{idProduct}", method = RequestMethod.GET)
	@ResponseBody
	public Product findProduct(@PathVariable("idProduct") String idProduct) {
		return productRepository.find(idProduct);
	}
	/**
	 * This method handles request and response to "getProducts" method of products repository.
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Product> getProducts() throws SQLException {
		return productRepository.getProducts();
	}
	/**
	 * This method handles request and response to "findByCategory" method of products repository.
	 * @param category
	 * @return Products list that match
	 */
	@RequestMapping(path = "/category/{category}", method = RequestMethod.GET)
	@ResponseBody
	public List<Product> findCategory(@PathVariable("category") String category) {
		return productRepository.findByCategory(category);
	}
	/**
	 * The method handles request and response to "update" method of products repository.
	 * @param product
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Product updateProduct(@RequestBody Product product) {
		return productRepository.update(product);
	}
	/**
	 * The method handles request and response to "delete" method of products repository.
	 * @param idProduct
	 */
	@RequestMapping(value = "/{idProduct}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void deleteProduct(@PathVariable("idProduct") String idProduct) {
		productRepository.delete(idProduct);
	}
	
	public static ProductRepository getRepository() {
		if(productRepository == null) {
			productRepository = new ProductRepository();
		}
		return productRepository;
	}
}
