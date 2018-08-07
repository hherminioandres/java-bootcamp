package com.globant.FinalProject.user;

import com.globant.FinalProject.DB.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class represents an user repository. The users information is stored into database.
 * This repository allows to insert, update, get and delete users.
 * @author Herminio Andres Hernandez
 * @version 01/08/2018
 */
public class UserRepository {

	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	
	public UserRepository() throws SQLException {
		connection = MySQLConnection.getConnection();
	}
	/**
	 * This method allows to add users. Executes a query that insert user information into database.
	 * @param user
	 * @return
	 * @throws SQLException
	 */
	public User addUser(User user) throws SQLException {
		preparedStatement = connection.prepareStatement("INSERT INTO USER(idUser, password, firstname, lastname, email) VALUES (?,?,?,?,?);");
		preparedStatement.setString(1, user.getUser());
		preparedStatement.setString(2, user.getPassword());
		preparedStatement.setString(3, user.getFirstname());
		preparedStatement.setString(4, user.getLastname());
		preparedStatement.setString(5, user.getEmail());
		int i = preparedStatement.executeUpdate();
		if(i > 0) {
			return user;
		}
		return null;
	}
	/**
	 * This method allow to find user through its id in the database.
	 * @param idUser
	 * @return
	 * @throws SQLException
	 */
	public User findUser(String idUser) throws SQLException {
		preparedStatement = connection.prepareStatement("SELECT * FROM USER WHERE idUser = ?;");
		preparedStatement.setString(1, idUser);
		ResultSet result = preparedStatement.executeQuery();
		User user = null;
		if(result.next()) {
			user =  new User(result.getString("idUser"), result.getString("password"), result.getString("firstname"), result.getString("lastname"),result.getString("email"));
		}
		return user;
	}
	/**
	 * This method executes the query that remove user from database. 
	 * @param idUser User id to remove
	 * @throws SQLException
	 */
	public void deleteUser(String idUser) throws SQLException {
		preparedStatement = connection.prepareStatement("DELETE FROM USER WHERE idUser=?;");
		preparedStatement.setString(1, idUser);
		preparedStatement.executeUpdate();
	}
	/**
	 * This method allow to find user through its real name.
	 * @param firstname 
	 * @param lastname
	 * @return
	 * @throws SQLException
	 */
	public User findByRealName(String firstname, String lastname) throws SQLException {
		preparedStatement = connection.prepareStatement("SELECT * FROM USER WHERE (firstname=? AND lastname=?);");
		preparedStatement.setString(1, firstname);
		preparedStatement.setString(2, lastname);
		ResultSet result = preparedStatement.executeQuery();
		User userAux = null;
		if(result.next()) {
			userAux =  new User(result.getString("idUser"), result.getString("password"), result.getString("firstname"), result.getString("lastname"),result.getString("email"));
		}
		return userAux;
	}
	/**
	 * This method allows to update user. Executes the update query.
	 * @param user
	 * @return
	 * @throws SQLException
	 */
	public User updateUser(User user) throws SQLException {
		preparedStatement = connection.prepareStatement("UPDATE USER SET password=?,firstname=?,lastname=?,email=? WHERE idUser=?;");
		preparedStatement.setString(1, user.getPassword());
		preparedStatement.setString(2, user.getFirstname());
		preparedStatement.setString(3, user.getLastname());
		preparedStatement.setString(4, user.getEmail());
		preparedStatement.setString(5, user.getUser());
		int i = preparedStatement.executeUpdate();
		if(i > 0) {
			return user; 
		}
		return null;
	}
}
