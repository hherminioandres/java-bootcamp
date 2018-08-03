package com.globant.user;

import java.util.Hashtable;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * This class represents an user repository. This repository allows to create and save new users
 * @author Herminio Andres Hernandez
 * @version 01/08/2018
 */
public class UserRepository {
	
	private Hashtable<String, User> users;
	
	public UserRepository() {
		users = new Hashtable<String, User>();
	}
	
	public User getUser(String user) {
		return users.get(user);
	}
	
	public boolean addUser(User user) {
		if(users.containsKey(user.getNickname())){
			return false;
		}
		users.put(user.getNickname(), user);
		return true;
	}

	public boolean containUser(String user) {
		return users.containsKey(user);
	}
	
	public boolean deleteUser(String user) {
		if(users.containsKey(user)) {
			users.remove(user);
			return true;
		}
		return false;
	}
	
	public User findUserByNick(String user) {
		return users.get(user);
	}
	
	public User findUserByName(String name, String lastname) {
		for(String user: users.keySet()) {
			if(users.get(user).getFirtname().equals(name) &&
					users.get(user).getLastname().equals(lastname)) {
				return users.get(user);
			}
		}
		return null;
	}
	
	public boolean updateMail(String user, String email) {
		User userAux = findUserByNick(user);
		if(userAux != null) {
			userAux.setEmail(email);
			return true;
		}
		return false;
	}
	public boolean updateFirstame(String user, String name) {
		User userAux = findUserByNick(user);
		if(userAux != null) {
			userAux.setFirtname(name);
			return true;
		}
		return false;
	}
	public boolean updateLastname(String user, String lastname) {
		User userAux = findUserByNick(user);
		if(userAux != null) {
			userAux.setLastname(lastname);
			return true;
		}
		return false;
	}
}
