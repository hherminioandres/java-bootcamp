package com.globant.service;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * "UserRepository" defines the behavior of a repository that contains users.
 * This class allows to create instances of "UserRepository" where users can be added, obtained or deleted.
 * @author Herminio Andres Hernandez
 * @version 21/07/2018
 */
public class UserRepository {
	//Attributes of the class
	/**
	 * Users are stored in the hashtable, where the user's nickname is the key and the user instance is the object of the same:
	 * <key, object> => <user.nickname, user>
	 */
	private Hashtable<String, User> users;
	/**
	 * Constructor of user repository
	 */
	public UserRepository() {
		users = new Hashtable<String, User>();
	}//Close constructor.
	/**
	 * The method allows to add a user on repository.
	 * @param user User to add. 
	 * @return User add on repository. Returns the recent user if it doesn't exist on the hashtable, else, returns null.
	 */
	public User addUser(User user) {
		if(users.containsKey(user.getNickname())){
			return null;
		}
		return users.put(user.getNickname(), user);
	}
	/**
	 * The method allows to obtain a user through of user's nickname.
	 * @param nickname User's nickname.
	 * @return User on repository.
	 */
	public User getUser(String nickname) {
			return users.get(nickname);
	}
	/**
	 * The method allows updating user's information. To do so, this method deletes old information user and adds the updated user.
	 * @param oldNickname User's nickname. This nickname is used to search the user.
	 * @param user Updated user instance to add. 
	 * @return User updated.
	 */
	public User updateUser(String oldNickname, User user) {
		if(deleteUser(oldNickname)) {
			return addUser(user);
		}
		return null;
	}
	/**
	 * The method allows to delete user of the repository.
	 * @param nickname User's nickname. This name is used to search the user on the hashtable.
	 * @return Boolean value of operation delete.
	 */
	public boolean deleteUser(String nickname) {
		if(users.containsKey(nickname)) {
			users.remove(nickname);
			return true;
		}
		return false;
	}
	/**
	 * The method allows to obtain all repository's users.
	 * To do so, this method iterates through the hash table and extracts the objects. This objects are users.
	 * @return Users list on repository.
	 */
	public ArrayList<User> getAllUsers(){
		ArrayList<User> userAux = new ArrayList<User>();
		for (String user : users.keySet()) {
			userAux.add(users.get(user));
		}
		return userAux;
	}
}
