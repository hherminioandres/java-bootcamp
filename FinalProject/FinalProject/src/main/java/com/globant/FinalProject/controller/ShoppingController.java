package com.globant.FinalProject.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.globant.FinalProject.DTO.DTOBuy;
import com.globant.FinalProject.shopping.ManagerCart;
import com.globant.FinalProject.shopping.Product;
import com.globant.FinalProject.shopping.ShoppingCart;
import com.globant.FinalProject.user.Authentication;
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

	private Hashtable<String, User> sessions = null;
	private ShoppingCart shoppingCart = null;
	private UserRepository userRepository = null;
	private ManagerCart managerCart = null;
	
	public ShoppingController() throws SQLException {
		sessions = new Hashtable<>();
		shoppingCart = new ShoppingCart();
		userRepository = UserRepositoryController.getRepository();
		managerCart = new ManagerCart();
	}
	/**
	 * The method allow to login.
	 * @param auth
	 * @return special code necessary for user identification. This code is sent by the header.
	 */
	@RequestMapping(path = "/login", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public String login(@RequestBody Authentication auth) {
		User userLogin = userRepository.authenticate(auth);
		if(userLogin != null) {
			String hashCode = hashCodeGenerator(userLogin);
			sessions.put(hashCode, userLogin);
			return "hash code: " + hashCode;
		}
		return "Incorrect login, try again";
	}
	/**
	 * The method allows to logout
	 * @param hash Special code, necessary for user identification
	 */
	@RequestMapping(path = "/logout", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void logout(@RequestHeader(value = "hash") String hash) {
		if(sessions.containsKey(hash)) {
			if(sessions.containsKey(hash)) {
				sessions.remove(hash);
			}
		}
	}
	/**
	 * This method handles request and response to "calculate" method of shopping cart.
	 * @param hash Special code, necessary for user identification
	 * @return 
	 */
	@RequestMapping(path = "/cart/calculate")
	@ResponseBody
	public float calculate(@RequestHeader(value = "hash") String hash) {
		if(sessions.containsKey(hash)) {
			return shoppingCart.calculate(sessions.get(hash));
		}
		return -1;
	}
	/**
	 * This method handles request and response to "add" method of cart.
	 * @param buy Product data to buy.
	 * @param hash Special code, necessary for user identification.
	 * @return result of the add operation.
	 */
	@RequestMapping(path = "/cart", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public boolean add(@RequestBody DTOBuy buy, @RequestHeader(value = "hash") String hash) {
		if(sessions.containsKey(hash)) {
			return shoppingCart.addProduct(sessions.get(hash), buy);
		}
		return false;
	}
	/**
	 * This method handles request and response to "deleteproduct" method to cart.
	 * @param code Product code to remove from the cart.
	 * @param hash Special code, necessary for user identification.
	 */
	@RequestMapping(path = "/cart/{code}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("code") String code, @RequestHeader(value = "hash") String hash) {
		if(sessions.containsKey(hash)) {
			shoppingCart.deleteProduct(sessions.get(hash), code);
		}
	}
	/**
	 * This method handles the request and the response to the "getProducts" method of cart.
	 * @param hash Special code, necessary for user identification.
	 * @return Products list into the cart
	 */
	@RequestMapping(path = "/cart", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<Product> getProducts(@RequestHeader(value = "hash") String hash) {
		if(sessions.containsKey(hash)) {
			return shoppingCart.getProducts(sessions.get(hash));
		}
		return null;
	}
	/**
	 * This method handles request and response to "productQuantity" method of cart.
	 * @param hash Special code, necessary for user identification.
	 * @return products quantity into the cart
	 */
	@RequestMapping(path = "/cart/quantity", method = RequestMethod.GET)
	@ResponseBody
	public int quantity(@RequestHeader(value = "hash") String hash) {
		if(sessions.containsKey(hash)) {
			return shoppingCart.productQuantity(sessions.get(hash));
		}
		return -1;
	}
	/**
	 * This method handles request and response to "processBuy" method.
	 * @param hash Special code, necessary for user identification
	 * @return result of pay operation
	 */
	@RequestMapping(path = "/cart/pay", method = RequestMethod.GET)
	@ResponseBody
	public boolean payBuy(@RequestHeader(value = "hash") String hash) {
		if(sessions.containsKey(hash)) {
			return managerCart.processBuy(sessions.get(hash), shoppingCart);
		}
		return false;
	}
	
	/**
	 * This method generates a special code for user identification
	 * @param user
	 * @return hash code
	 */
	public String hashCodeGenerator(User user) {
		return user.getUser()+"1234";
	}
}
