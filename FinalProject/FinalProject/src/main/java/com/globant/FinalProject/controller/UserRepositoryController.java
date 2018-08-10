package com.globant.FinalProject.controller;

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
 * This controller handles requests for the users repository.
 * @author Herminio Andres Hernandez
 * @version 01/08/2018
 */
@Controller
@RequestMapping("/users")
public class UserRepositoryController {

	static UserRepository userRepository;
	
	public UserRepositoryController() {
		userRepository = getRepository();
	}
	/**
	 * The method handles request and response to "add" method of users repository. 
	 * @param user
	 * @return created user
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public User createUser(@RequestBody User user) {
		return userRepository.add(user);
	}
	/**
	 * This method handles request and response to "delete" method of users repository. 
	 * @param user idUser to remove
	 */
	@RequestMapping(value = "/{user}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void deleteUser(@PathVariable("user") String user) {
		userRepository.delete(user);
	}
	/**
	 * This method handles request and response to "find" method of users repository. 
	 * @param user
	 * @return user that math with given idUser
	 */
	@RequestMapping(value = "/{user}", method = RequestMethod.GET)
	@ResponseBody
	public User findUser(@PathVariable("user") String user) {
		return userRepository.find(user);
	}
	/**
	 * This method handles request and response to "update" method of users repository
	 * @param user 
	 * @return updated user
	 */
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public User updateUser(@RequestBody User user) {
		return userRepository.update(user);
	}
	/**
	 * The method handles request and response to "findByRealName" method of users repository
	 * @param firstname
	 * @param lastname
	 * @return user that matches the given name and surname
	 * **/
	@RequestMapping(value = "/{firstname}/{lastname}", method = RequestMethod.GET)
	@ResponseBody
	public User findRealName(@PathVariable("firstname") String firstname, @PathVariable("lastname") String lastname) {
		return userRepository.findByRealName(firstname, lastname);
	}

	public static UserRepository getRepository() {
		if(userRepository == null) {
			userRepository = new UserRepository();
		}
		return userRepository;
	}
}