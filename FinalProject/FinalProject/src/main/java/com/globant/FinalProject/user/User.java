package com.globant.FinalProject.user;

/**
 * This class represents a user. This is a composite of information as firstname, lastname, nickname and email
 * @author Herminio Andres Hernandez
 * @version 01/08/2018
 */
public class User implements Comparable<User>{

	private String firstname;
	private String lastname;
	private String idUser;
	private String email;
	private String password;
	
	public User(String idUser, String password, String firstname, String lastname, String email) {
		this.idUser = idUser;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
	}
	
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public boolean setLastname(String lastname) {
		this.lastname = lastname;
		return true;
	}

	public String getUser() {
		return idUser;
	}

	public void setUser(String userName) {
		this.idUser = userName;
	}

	public String getEmail() {
		return email;
	}

	public boolean setEmail(String email) {
		this.email = email;
		return true;
	}

	public int compareTo(User secondUser) {
		if(this.idUser.equals(secondUser.getUser())){
			return 1;
		}
		return 0;
	}
	
}
