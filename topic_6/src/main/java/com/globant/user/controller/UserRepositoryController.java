package com.globant.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.globant.user.User;
import com.globant.user.UserRepository;

/**
 * This controller handle request input and generate response output of users repository
 * @author Herminio Andres Hernandez
 * @version 01/08/2018
 */
@RestController
@RequestMapping("/registration")
public class UserRepositoryController {

	UserRepository userRepository = new UserRepository();

	@RequestMapping("/createUser")
	public boolean createUser(@RequestParam(value="user") String user, @RequestParam(value="name") String name, @RequestParam(value="lastname") String lastname, @RequestParam(value="email") String email, @RequestParam(value="pass") String pass) {
		return userRepository.addUser(new User(user, name, lastname, email, pass));
	}
	@RequestMapping("/deleteUser")
	public boolean deleteUser(@RequestParam(value="user") String user) {
		return userRepository.deleteUser(user);
	}
	@RequestMapping("/findUser")
	public User findUser(@RequestParam(value="user") String user) {
		return userRepository.getUser(user);
	}
	@RequestMapping("/updateMail")
	public void updateMail(@RequestParam(value="user") String user, @RequestParam(value="email") String email) {
		userRepository.updateMail(user, email);
	}
	@RequestMapping("/updateFirstname")
	public void updateFirstname(@RequestParam(value="user") String user, @RequestParam(value="firstname") String firstname) {
		userRepository.updateMail(user, firstname);
	}
	@RequestMapping("/updateLastname")
	public void updateLastname(@RequestParam(value="user") String user, @RequestParam(value="lastname") String lastname) {
		userRepository.updateMail(user, lastname);
	}
	@RequestMapping("/findRealName")
	public User findRealName(@RequestParam(value="firstname") String firstname, @RequestParam(value="lastname") String lastname) {
		return userRepository.findUserByName(firstname, lastname);
	}
}