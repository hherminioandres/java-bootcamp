package com.globant.school;

import java.util.ArrayList;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

/**
 * This class represents a course
 * @author Herminio Andres Hernandez
 * @version 29-07-2018
 */
@Entity("course")
public class Course{

	@Id
	private String name;
	@Property("hours")
	private float hours;
	@Embedded
	private Teacher teacher;
	@Embedded
	private ArrayList<Schedule> scheduler; 

	public Course() {
		
	}
	
	public Course(String name, float hours, Teacher teacher) {
		this.name = name;
		this.hours = hours;
		this.teacher = teacher;
		scheduler = new ArrayList<Schedule>();
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
	
	public ArrayList<Schedule> getScheduler() {
		return scheduler;
	}

	public void setScheduler(ArrayList<Schedule> scheduler) {
		this.scheduler = scheduler;
	}
}
