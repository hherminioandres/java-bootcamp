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
 * This class allows you to connect to MongoDB and access it. The Morphia library is used to map data to Mongo.
 *  * @author Herminio Andres Hernandez
 * @version 29-07-2018
 */
public class MongoConnection {
	
	public static final String LOCALHOST = "127.0.0.1";
	public static final int PORT = 27017;
	public static final String DBNAME = "highschool";
	
	Datastore datastore;
	StudentDAO studentDAO;

	public MongoConnection() {
		this.configuration();
	}
	
	public void configuration() {
	     MongoClient mongo = new MongoClient(new ServerAddress(LOCALHOST, PORT));
	     Morphia morphia = new Morphia();
	     datastore = morphia.createDatastore(mongo, DBNAME);
	     studentDAO = new StudentDAO(datastore);
	}
	/**
	 * The method shows all students that approved a given course.
	 * Into the method, a query is executed to obtain all the students whose notes in a specific course were greater than 4.
	 * @param course Given course
	 */
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
