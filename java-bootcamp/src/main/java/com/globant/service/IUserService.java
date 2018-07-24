package com.globant.service;

/**
 * IUserService defines headers of user service.
 * This class defines basics operations to implement such as Create, Read, Update and Delete user.
 * @author Herminio Andres Hernandez
 * @version 21/07/2018
 */

public interface IUserService {
	
	/**
	 * This method defines header to create a user.
	 * @param nickname User's nickname. The nick is used to create the user.
	 * @return User to create.
	 */
	public User create(String nickname);
	/**
	 * This method defines header to read or obtain a user.
	 * @param nickname User's nickname. This is used to search the required user.
	 * @return User to obtain.
	 */
	public User read(String nickname);
	/**
	 * This method defines header to update a user. 
	 * @param oldNickname User's actual or old nickname. This is used to search the user to update.
	 * @param user User to update.
	 * @return User to update.
	 */
	public User update(String oldNickname, User user);
	/**
	 * This method defines header to delete a user.  
	 * @param nickname User's nickname. This is used to search the user to delete.
	 * @return Boolean value of operation delete.
	 */
	public boolean delete(String nickname);
}
