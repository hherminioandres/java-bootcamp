package com.globant.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Herminio Andres Hernandez
 */
public class MySQLAccess {
	
    private Connection connect = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    /**
     * This method allows to obtain teacher's schedule.
     * @param matriculation Teacher's matriculation.
     * @throws Exception
     */
    public void readTeacherSchedules(String matriculation) throws Exception {
        try {
        	connect = DriverManager.getConnection("jdbc:mysql://localhost/HIGHSCHOOL", "herminio", "1234");
        	
        	statement = connect.createStatement();

        	resultSet = statement.executeQuery("SELECT CONCAT(t.lastname,', ', t.firstname) AS \"Teacher\", s.day as \"Day\", CONCAT(s.begin,' - ',s.end) AS \"Time\", c.name AS \"Course\"\r\n" + 
        			"FROM TEACHER t\r\n" + 
        			"INNER JOIN COURSE c ON (c.teacher = t.matriculation)\r\n" + 
        			"INNER JOIN SCHEDULE s ON (s.course = c.name)\r\n" + 
        			"WHERE t.matriculation LIKE \""+matriculation+"\"\r\n" + 
        			"ORDER BY CASE\r\n" + 
        			"          WHEN Day = 'Sunday' THEN 1\r\n" + 
        			"          WHEN Day = 'Monday' THEN 2\r\n" + 
        			"          WHEN Day = 'Tuesday' THEN 3\r\n" + 
        			"          WHEN Day = 'Wednesday' THEN 4\r\n" + 
        			"          WHEN Day = 'Thursday' THEN 5\r\n" + 
        			"          WHEN Day = 'Friday' THEN 6\r\n" + 
        			"          WHEN Day = 'Saturday' THEN 7\r\n" + 
        			"     END, s.begin;");
        	writeTeacherSchedules(resultSet);
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }
    /**
     * This method show teacher's schedule with day, time and course.
     * @param resultSet this result is query result. 
     * @throws SQLException 
     */
    private void writeTeacherSchedules(ResultSet resultSet) throws SQLException {
    	if(resultSet.first()) {
    		String teacher = resultSet.getString("Teacher");
    		System.out.println("Teacher: "+teacher);
    		System.out.println("Schedule:");
    	}
    	resultSet.previous();
        while(resultSet.next()) {   
            String day = resultSet.getString("Day");
            String time = resultSet.getString("Time");
            String course = resultSet.getString("Course");
            System.out.println("	"+day + " " +time+" "+course);
        }
    }
    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }
    public static void main(String[] args) throws Exception {    
    	MySQLAccess db = new MySQLAccess();
    	
    	/**
    	 * Teacher matriculation
    	 */
    	String matriculation = "mn002";
    	
    	db.readTeacherSchedules(matriculation);
    }
}