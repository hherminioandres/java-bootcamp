package com.globant.user;

/**
 * This class represents a user. This is a composite of information as firstname, lastname, nickname and email
 * @author Herminio Andres Hernandez
 * @version 01/08/2018
 */
public class User {

	private String firstname;
	private String lastname;
	private String user;
	private String mail;
	private String password;
	
	public User(String user, String firstname, String lastname, String mail, String password) {
		this.user = user;
		this.firstname = firstname;
		this.lastname = lastname;
		this.mail = mail;
		this.password = password;
	}
	
	public String getFirtname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}

	public String getLastname() {
		return lastname;
	}

	public boolean setLastname(String lastname) {
		this.lastname = lastname;
		return true;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String userName) {
		this.user = userName;
	}

	public String getEmail() {
		return mail;
	}

	public boolean setEmail(String email) {
		this.mail = email;
		return true;
	}
	
}
