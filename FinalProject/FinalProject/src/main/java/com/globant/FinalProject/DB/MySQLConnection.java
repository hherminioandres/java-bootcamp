package com.globant.FinalProject.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class connects and returns a database connection
 * @author Herminio Andres Hernandez
 */
public class MySQLConnection {
	private static String URL = "jdbc:mysql://localhost/shoppingdb";
	private static String USER = "jdbc:mysql://localhost/shoppingdb";
	private static String PASS = "jdbc:mysql://localhost/shoppingdb";
	public static Connection connection = null;

	public static Connection getConnection() throws SQLException {
		if(connection == null)
			connection = DriverManager.getConnection(URL, USER, PASS);
		return connection;
	}
	
	public static void close() throws SQLException {
		if(connection != null)
			connection.close();
	}
}
