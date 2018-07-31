package com.globant.dao;

import java.util.ArrayList;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.QueryResults;
import com.globant.school.Record;
import com.globant.school.Student;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

/**
 * 
 * @author Herminio Andres Hernandez
 * @version 29-07-2018
 */
public class MongoConnection {
	
	Datastore datastore;
	StudentDAO studentDAO;
	
	public MongoConnection() {
		this.configuration();
	}
	
	public void configuration() {
	     MongoClient mongo = new MongoClient(new ServerAddress("127.0.0.1", 27017));
	     Morphia morphia = new Morphia();
	     datastore = morphia.createDatastore(mongo, "highschool");
	     studentDAO = new StudentDAO(datastore);
	}
	
	public void getApprovedStudents(String course) {
		org.mongodb.morphia.query.Query<Student> query = datastore.createQuery(Student.class);
		query.and(query.criteria("historical._id").equal(course), query.criteria("historical.final").greaterThanOrEq(4.0f));
		QueryResults<Student> resultStudents = studentDAO.find(query);
		System.out.println("Course: "+ course + '\n');
		System.out.println("Students: " + '\n');
	    for (Student student : resultStudents) {
	        System.out.println(student.getLastname()+", "+student.getFirstname() + " - note: "+student.getUniqueHistory(course).getFinalC());   
	        System.out.println("");
	    }
	}
	
	public StudentDAO getStudentDAO() {
		return studentDAO;
	}

	public static void main(String[] args) {
		MongoConnection mongo = new MongoConnection();
		StudentDAO studentDAO = mongo.getStudentDAO();
		/**COURSES**/
	    ArrayList<String> courses = new ArrayList<String>();
	    courses.add("Algebra");
	    courses.add("Math");
	    /**HISTORICAL**/
	    Record record = new Record("Robotics", 4,8,7,10);
	    record.setFinalC(10);
	    Record record2 = new Record("Programing", 4,8,7,10);
	    record2.setFinalC(10);
	    ArrayList<Record> records = new ArrayList<Record>();
	    records.add(record);
	    records.add(record2);
	    Student student = new Student("stt_011","Lujan","Rosello", "1995-12-17");
	    student.setCourses(courses);
	    student.setHistory(records);    
	    studentDAO.save(student);
	    mongo.getApprovedStudents("Algebra");
	}
}
