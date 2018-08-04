package com.globant.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.globant.user.User;
import com.globant.user.UserRepository;

/**
 * This controller handle request input and generate response output of users repository
 * @author Herminio Andres Hernandez
 * @version 01/08/2018
 */
@Controller
@RequestMapping("/registration")
public class UserRepositoryController {

	UserRepository userRepository = new UserRepository();

	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public User createUser(@RequestBody User user) {
		return userRepository.addUser(user);
	}
	@RequestMapping(value = "/deleteUser/{user}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void deleteUser(@PathVariable("user") String user) {
		userRepository.deleteUser(user);
	}
	@RequestMapping(value = "/findUser/{user}", method = RequestMethod.GET)
	@ResponseBody
	public User findUser(@PathVariable("user") String user) {
		return userRepository.getUser(user);
	}
	@RequestMapping(value = "/updateUser", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public User updateUser(@RequestBody User user) {
		return userRepository.updateUser(user);
	}
	@RequestMapping(value = "/findRealName/{firstname}/{lastname}", method = RequestMethod.GET)
	@ResponseBody
	public User findRealName(@PathVariable("firstname") String firstname, @PathVariable("lastname") String lastname) {
		return userRepository.findByRealName(firstname, lastname);
	}
}