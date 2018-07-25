package com.globant.service;

/**
 * "UserService" implements to "IUserService".
 * @author Herminio Andres Hernandez
 * @version 21/07/2018
 */

public class UserService implements IUserService{

	private UserRepository userRepository;

	public UserService() {
		userRepository = new UserRepository();
	}
	
	public User create(String nickname) {
		User user = new User(nickname);
		return userRepository.addUser(user);
	}

	public User read(String nickname) {
		return userRepository.getUser(nickname);
	}

	public User update(String oldNickname, User user) {
		return userRepository.updateUser(oldNickname, user);
	}
	
	public boolean delete(String nickname) {
		return userRepository.deleteUser(nickname);
	}
}
