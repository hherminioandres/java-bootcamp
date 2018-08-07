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

import com.globant.FinalProject.DTO.DTOBuy;
import com.globant.FinalProject.shopping.ManagerCart;
import com.globant.FinalProject.shopping.Product;
import com.globant.FinalProject.shopping.ShoppingCart;
import com.globant.FinalProject.user.Registration;
import com.globant.FinalProject.user.User;
import com.globant.FinalProject.user.UserRepository;

/**
 * This controller handles requests for the shopping center
 * @author Herminio Andres Hernandez
 * @version 05/08/2018
 */
@Controller
@RequestMapping("/api")
public class ShoppingController {

	private User currentUser = null;
	private ShoppingCart shoppingCart = null;
	private UserRepository userRepository = null;
	private ManagerCart managerCart = null;
	
	public ShoppingController() throws SQLException {
		userRepository = UserRepositoryController.getRepository();
		managerCart = new ManagerCart();
	}
	
	@RequestMapping(path = "/login", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public User login(@RequestBody  Registration registration) throws SQLException {
		User userLogin = userRepository.find(registration.getIdUser());
		if(userLogin != null) {
			if(userLogin.getPassword().equals(registration.getPassword())) {
				currentUser = userLogin;
				shoppingCart = new ShoppingCart(userLogin);
			}
		}
		return currentUser;
	}
	
	@RequestMapping(path = "/logout/{idUser}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void logout(@PathVariable("idUser") String idUser) throws SQLException {
		if(currentUser.getUser().equals(idUser)) {
			currentUser = null;
			shoppingCart = null;
		}
	}
	
	@RequestMapping(path = "/cart/calculate")
	@ResponseBody
	public float calculate() throws SQLException {
		return shoppingCart.calculate();
	}
	
	@RequestMapping(path = "/cart", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public boolean add(@RequestBody DTOBuy buy) throws SQLException {
		return shoppingCart.addProduct(buy);
	}
	
	@RequestMapping(path = "/cart/{code}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("code") String code) throws SQLException {
		shoppingCart.deleteProduct(code);
	}
	
	@RequestMapping(path = "/cart", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<Product> getProducts() throws SQLException{
		return shoppingCart.getProducts();
	}
	
	@RequestMapping(path = "cart/quantity", method = RequestMethod.GET)
	@ResponseBody
	public int quantity() throws SQLException {
		return shoppingCart.productQuantity();
	}
	
	@RequestMapping(path = "cart/pay", method = RequestMethod.GET)
	@ResponseBody
	public boolean payBuy() throws SQLException {
		return managerCart.processBuy(shoppingCart);
	}
}
