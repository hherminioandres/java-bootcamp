package com.globant.school;

import java.sql.Time;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Property;

/**
 * This class represents a schedule that contains a day and begin and end times corresponding to a course.
 * @author Herminio Andres Hernandez
 * @version 31-07-2018
 */
@Embedded
public class Schedule {
	
	@Property("day")
	private String day;
	@Property("begin")
	private Time begin;
	@Property("end")
	private Time end;
	
	public Schedule() {
		
	}
	
	public Schedule (String day, Time begin, Time end) {
		this.day = day;
		this.begin = begin;
		this.end = end;
	}
	
	public String getDay() {
		return day;
	}
	
	public void setDay(String day) {
		this.day = day;
	}
	
	public Time getBegin() {
		return begin;
	}
	
	public void setBegin(Time begin) {
		this.begin = begin;
	}
	
	public Time getEnd() {
		return end;
	}
	
	public void setEnd(Time end) {
		this.end = end;
	}
}
