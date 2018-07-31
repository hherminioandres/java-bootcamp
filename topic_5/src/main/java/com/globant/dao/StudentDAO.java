package com.globant.dao;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import com.globant.school.Student;

/**
 * 
 * @author Herminio Andres Hernandez
 * @version 29-07-2018
 */
public class StudentDAO extends BasicDAO<Student, Object> {

	protected StudentDAO(Datastore ds) {
		super(ds);
	}
}