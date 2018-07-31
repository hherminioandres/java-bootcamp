package com.globant.school;

import java.util.ArrayList;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

/**
 * 
 * @author Herminio Andres Hernandez
 * @version 29-07-2018
 */
@Entity("teacher")
public class Teacher {
	
	@Id
	private String matriculation;
	@Property("firstname")
	private String firstname;
	@Property("lastname")
	private String lastname;
	@Property("birth")
	private String birth;
	@Embedded
	private ArrayList<Course> courses;
	
	public Teacher(String matriculation, String firstname, String lastname) {
		this.matriculation = matriculation;
		this.firstname = firstname;
		this.lastname = lastname;
		birth = "2018-10-10";
		//courses = new Repository<Course>();
		
	}
	
	public String getMatriculation() {
		return matriculation;
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

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public ArrayList<Course> getCourses() {
		return courses;
	}

	public void setCourses(ArrayList<Course> courses) {
		this.courses = courses;
	}
}
