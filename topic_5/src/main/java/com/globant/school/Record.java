package com.globant.school;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;
/**
 * This class represents a course history into student. Record contains course name and calcifications of the three partials and final.
 * @author Herminio Andres Hernandez
 * @version 29-07-2018
 */
@Embedded
public class Record {
	
	@Id
	private String course;
	@Property("partial1")
	private float partial1;
	@Property("partial2")
	private float partial2;
	@Property("partial3")
	private float partial3;
	@Property("final")
	private float finalC;
	
	public Record() {
		
	}
	public Record(String course, float partial1, float partial2, float partial3, float finalC) {
		this.course = course;
		this.partial1 = partial1;
		this.partial2 = partial2;
		this.partial3 = partial3;
		this.finalC = finalC;
	}
	
	public String getCourse() {
		return course;
	}

	public float getPartial1() {
		return partial1;
	}

	public void setPartial1(float partial1) {
		this.partial1 = partial1;
	}

	public float getPartial2() {
		return partial2;
	}

	public void setPartial2(float partial2) {
		this.partial2 = partial2;
	}

	public float getPartial3() {
		return partial3;
	}

	public void setPartial3(float partial3) {
		this.partial3 = partial3;
	}

	public float getFinalC() {
		return finalC;
	}

	public void setFinalC(float finalC) {
		this.finalC = finalC;
	}	
}
