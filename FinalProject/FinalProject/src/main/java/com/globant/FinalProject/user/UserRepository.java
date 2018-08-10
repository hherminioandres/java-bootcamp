package com.globant.FinalProject.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.globant.FinalProject.DAO.MySQLConnection;
import com.globant.FinalProject.Repositry.IRepository;

/**
 * This class represents an user repository. The users information is stored into database.
 * This repository allows to insert, update, get and delete users.
 * @author Herminio Andres Hernandez
 * @version 01/08/2018
 */
public class UserRepository implements IRepository<User>{

	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet result = null;
	
	public UserRepository() {

	}
	/**
	 * This method allows to add users. Executes a query that insert user information into database.
	 * @param user
	 * @return
	 * @throws SQLException
	 */
	public User add(User user) {
		try {
			connection = MySQLConnection.getConnection();
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
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return null;
	}
	/**
	 * This method allow to find user through its id in the database.
	 * @param idUser
	 * @return
	 * @throws SQLException
	 */
	public User find(String idUser) {
		User user = null;
		try {
			connection = MySQLConnection.getConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM USER WHERE idUser = ?;");
			preparedStatement.setString(1, idUser);
			result = preparedStatement.executeQuery();
			if(result.next()) {
				user =  new User(result.getString("idUser"), result.getString("password"), result.getString("firstname"), result.getString("lastname"),result.getString("email"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// TODO: handle finally clause
		}
		return user;
	}
	/**
	 * This method executes the query that remove user from database. 
	 * @param idUser User id to remove
	 * @throws SQLException
	 */
	public void delete(String idUser) {
		try {
			connection = MySQLConnection.getConnection();
			preparedStatement = connection.prepareStatement("DELETE FROM USER WHERE idUser=?;");
			preparedStatement.setString(1, idUser);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
	}
	/**
	 * This method allow to find user through its real name.
	 * @param firstname 
	 * @param lastname
	 * @return
	 * @throws SQLException
	 */
	public User findByRealName(String firstname, String lastname) {
		User user = null;
		try {
			connection = MySQLConnection.getConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM USER WHERE (firstname=? AND lastname=?);");
			preparedStatement.setString(1, firstname);
			preparedStatement.setString(2, lastname);
			result = preparedStatement.executeQuery();
			if(result.next()) {
				user =  new User(result.getString("idUser"), result.getString("password"), result.getString("firstname"), result.getString("lastname"),result.getString("email"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return user;
	}
	/**
	 * This method allows you to obtain a user from the repository if it matches the given idUser and password
	 * @param auth Authentication data
	 * @return 
	 */
	public User authenticate(Authentication auth) {
		User user = null;
		try {
			connection = MySQLConnection.getConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM USER WHERE (idUser=? AND password=?);");
			preparedStatement.setString(1, auth.getIdUser());
			preparedStatement.setString(2, auth.getPassword());
			result = preparedStatement.executeQuery();
			if(result.next()) {
				user =  new User(result.getString("idUser"), result.getString("password"), result.getString("firstname"), result.getString("lastname"),result.getString("email"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return user;
	}
	/**
	 * This method allows to update user. Executes the update query.
	 * @param user
	 * @return
	 * @throws SQLException
	 */
	public User update(User user) {
		try {
			connection = MySQLConnection.getConnection();
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
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return null;
	}
	public void close() {
		try {
			if(connection != null) {
				connection.close();
			}
			if(preparedStatement != null) {
				preparedStatement.close();
			}
			if(result != null) {
				result.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
