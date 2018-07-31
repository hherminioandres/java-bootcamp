package com.globant.school;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * 
 * @author Herminio Andres Hernandez
 * @version 29-07-2018
 */
@Entity("course")
public class Course{

	@Id
	private String name;	
	@Embedded
	private Teacher teacher;
	
	public Course() {
		name =  "";
		teacher = null;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Teacher getTeacher() {
		return teacher;
	}
	
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
}
