package com.globant.FinalProject.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class connects and returns a database connection
 * @author Herminio Andres Hernandez
 */
public class MySQLConnection {
	private static String URL = "jdbc:mysql://localhost/shoppingdb";
	private static String USER = "root";
	private static String PASS = "1234";
	public static Connection connection = null;

	public static Connection getConnection() throws SQLException {
		connection = DriverManager.getConnection(URL, USER, PASS);
		return connection;
	}
	
	public static void close() throws SQLException {
		if(connection != null)
			connection.close();
	}
}
