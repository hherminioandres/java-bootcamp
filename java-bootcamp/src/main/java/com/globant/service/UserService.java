package com.globant.service;

/**
 * "UserService" defines behavior a user service, implements to "IUserService".
 * Implements basics operations such as Create, Read, Update and Delete user.
 * @author Herminio Andres Hernandez
 * @version 21/07/2018
 */

public class UserService implements IUserService{
	//Attributes of the class.
	private UserRepository userRepository;
	/**
	 * Constructor of user service.
	 */
	public UserService() {
		userRepository = new UserRepository();
	}//Close constructor
	/**
	 * The method allows to create user. 
	 * @param nickname User's nickname. The nick is used to create the user.
	 * @return Instance of the created user.
	 */
	public User create(String nickname) {
		User user = new User(nickname);
		return userRepository.addUser(user);
	}
	/**
	 * The method allows to obtain user.
	 * @param nickname User's nickname. This is used to search the required user.
	 * @return Required user instance.
	 */
	public User read(String nickname) {
		return userRepository.getUser(nickname);
	}
	/**
	 * The method allows updating an existing user.
	 * @param oldNickname User's actual or old nickname. This is used to search the user to update.
	 * @param user Updated user instance.
	 * @return User update.
	 */
	public User update(String oldNickname, User user) {
		return userRepository.updateUser(oldNickname, user);
	}
	/**
	 * The method allows to delete user. 
	 * @param nickname User's nickname. This is used to search the user to delete.
	 */
	public boolean delete(String nickname) {
		return userRepository.deleteUser(nickname);
	}
}
