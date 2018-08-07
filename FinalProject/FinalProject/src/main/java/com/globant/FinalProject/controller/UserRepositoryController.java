package com.globant.FinalProject.controller;

import java.sql.SQLException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.globant.FinalProject.user.User;
import com.globant.FinalProject.user.UserRepository;

/**
 * This controller handle request input and generate response output of users repository.
 * Allows add, remove and update users on repository.
 * @author Herminio Andres Hernandez
 * @version 01/08/2018
 */
@Controller
@RequestMapping("/")
public class UserRepositoryController {

	static UserRepository userRepository;
	
	public UserRepositoryController() throws SQLException {
		userRepository = getRepository();
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public User createUser(@RequestBody User user) throws SQLException {
		return userRepository.addUser(user);
	}
	
	@RequestMapping(value = "/users/{user}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void deleteUser(@PathVariable("user") String user) throws SQLException {
		userRepository.deleteUser(user);
	}
	
	@RequestMapping(value = "/users/{user}", method = RequestMethod.GET)
	@ResponseBody
	public User findUser(@PathVariable("user") String user) throws SQLException {
		return userRepository.findUser(user);
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public User updateUser(@RequestBody User user) throws SQLException {
		return userRepository.updateUser(user);
	}
	
	@RequestMapping(value = "/users/{firstname}/{lastname}", method = RequestMethod.GET)
	@ResponseBody
	public User findRealName(@PathVariable("firstname") String firstname, @PathVariable("lastname") String lastname) throws SQLException {
		return userRepository.findByRealName(firstname, lastname);
	}

	public static UserRepository getRepository() throws SQLException{
		if(userRepository == null) {
			userRepository = new UserRepository();
		}
		return userRepository;
	}
}