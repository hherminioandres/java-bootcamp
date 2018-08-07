package com.globant.FinalProject.user;

/**
 * This class represents a user registration that contains idUser and password. Is used to login.
 * @author her_1
 *
 */
public class Registration {

	private String idUser;
	private String password;
	
	public Registration(String idUser, String password) {
		this.idUser = idUser;
		this.password = password;
	}
	public String getIdUser() {
		return idUser;
	}
	public String getPassword() {
		return password;
	}
}
