package com.globant.service;

/**
 * IUserService defines headders of user service.
 * This class Defines basics operations to implement such as Create, Read, Update and Delete user.
 * @author Herminio Andres Hernandez
 * @version 21/07/2018
 */

public interface IUserService {
	
	/**
	 * This method defines headder to create a user.
	 * @param nickname User's nickname.
	 * @return User to create.
	 */
	public User create(String nickname);
	/**
	 * This method defines headder to read or obtain a user.
	 * @param nickname User's nickname.
	 * @return User to obtain.
	 */
	public User read(String nickname);
	/**
	 * This method defines headder to update a user. 
	 * @param oldNickname User's actual or old nickname.
	 * @param user User to update.
	 * @return User to update.
	 */
	public User update(String oldNickname, User user);
	/**
	 * This method defines headder to delete a user.  
	 * @param nickname User's nickname.
	 * @return Boolean value of operation delete.
	 */
	public boolean delete(String nickname);
	
}
