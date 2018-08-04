package com.globant.user;

import java.util.Hashtable;

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
	
	public User addUser(User user) {
		if(users.containsKey(user.getUser())){
			return null;
		}
		users.put(user.getUser(), user);
		return user;
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
	
	public User findByRealName(String name, String lastname) {
		for(String user: users.keySet()) {
			if(users.get(user).getFirtname().equals(name) &&
					users.get(user).getLastname().equals(lastname)) {
				return users.get(user);
			}
		}
		return null;
	}
	
	public User updateUser(User user) {
		if(users.containsKey(user.getUser())) {
			users.put(user.getUser(), user);
			return user;
		}
		return null;
	}
}
