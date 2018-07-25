package com.globant.service;

/**
 * "User" class represents a specific user.
 * This is a combination of a nickname, name, surname, nationality and age.
 * This class allows to set and get the user's properties
 * @author Herminio Andres Hernandez
 * @version 21/07/2018
 */

public class User {
	//Attributes of the class.
	private String nickname;
	private String name;
	private String surname;
	private String nationality;
	private int age;
	/**
	 * Constructor User.
	 * @param nickname User's nickname to create.
	 */
	public User(String nickname) {
		this.nickname = nickname;
	}//Close constructor
	public String getNickname() {
		return nickname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}
