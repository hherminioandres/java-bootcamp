package com.globant.school;

import java.util.ArrayList;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

/**
 * This class represents a student
 * @author Herminio Andres Hernandez
 * @version 29-07-2018
 */
@Entity("student")
public class Student{

	@Id
	private String _id;
	@Property("firstname")
	private String firstname;
	@Property("lastname")
	private String lastname;
	@Property("birth")
	private String birth;
	@Property("study")
	private ArrayList<String> study;
	@Embedded
	private ArrayList<Record> historical; 

	public Student() {
		
	}
	
	public Student(String registration, String firstname, String lastname, String birth) {
		this._id = registration;
		this.firstname = firstname;
		this.lastname = lastname;
		this.birth = birth;
	}
	
	public String getRegistration() {
		return _id;
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

	public ArrayList<String> getCourses() {
		return study;
	}
	
	public void setCourses(ArrayList<String> courses) {
		this.study = courses;
	}

	public ArrayList<Record> getHistory() {
		return historical;
	}
	
	public Record getUniqueHistory(String course){
		for(Record co: historical) {
			if(co.getCourse().equals(course)) {
				return co;
			}
		}
		return null;
	}
	
	public void setHistory(ArrayList<Record> history) {
		this.historical = history;
	}
}
