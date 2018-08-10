package com.globant.FinalProject.shopping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.globant.FinalProject.DAO.MySQLConnection;
import com.globant.FinalProject.user.User;

import java.sql.Date;

/**
 * This class handles the cart after paying for the buy. Generates the tickets that
 * refer to this buy and save all the information into database.
 * @author Herminio Andres Hernandez
 * @version 05/08/2018
 */
public class ManagerCart {
	
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet result = null;
	private Statement statement = null; 
	private int ticketNumber = -1;
	
	public ManagerCart() throws SQLException {
		connection = MySQLConnection.getConnection();
		ticketNumber = getNumberTicket();
	}
	/**
	 * This method allows to save the buy into the DB 
	 * @param shoppingCart 
	 * @return result of the save operation
	 * @throws SQLException
	 */
	public boolean processBuy(User user, ShoppingCart shoppingCart) {
		boolean bool = saveBuy(user,shoppingCart);
		if(bool) {
			return shoppingCart.deleteAllProducts(user);
		}
		return false;
	}
	/**
	 * The method allows to insert ticket row into database. Several ticket row refer to  a given ticket.
	 * @param shoppingCart 
	 * @return result of the insert operation
	 * @throws SQLException
	 */
	private boolean saveBuy(User user, ShoppingCart shoppingCart) {
		ArrayList<Product> products = shoppingCart.getProducts(user);
		try {
			if(!products.isEmpty()) {
				if(createTicket(user.getUser())) {
					connection = MySQLConnection.getConnection();
					for(Product product: products) {
						Statement statement = connection.createStatement();
						result = statement.executeQuery("SELECT value FROM PRODUCT WHERE idProduct =  \""+product.getIdProduct()+"\";");
						if(result.first()) {
							preparedStatement = connection.prepareStatement("INSERT INTO TICKET_ROW(idTicket, idUser, idProduct, quantity, unitValue) VALUES (?,?,?,?,?);");
							preparedStatement.setInt(1, ticketNumber);
							preparedStatement.setString(2, user.getUser());
							preparedStatement.setString(3, product.getIdProduct());
							preparedStatement.setInt(4, product.getQuantity());
							preparedStatement.setFloat(5, result.getFloat("value"));
							preparedStatement.executeUpdate();
						}
					}
					ticketNumber++;
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return false;
	} 
	/**
	 * The method inserts the tickets into database. This ticket are saved in "Ticket" table
	 * @param idUser
	 * @return
	 * @throws SQLException
	 */
	private boolean createTicket(String idUser) {
		try {
			connection = MySQLConnection.getConnection();
			preparedStatement = connection.prepareStatement("INSERT INTO TICKET(idUser, dateBuy) VALUES (?,?);");
			preparedStatement.setString(1, idUser);
			preparedStatement.setDate(2, new Date(0));
			int i = preparedStatement.executeUpdate();
			if(i > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return false;
	}
	/**
	 * This method execute a query to obtain last ticket number, because idTicket is auto increment.
	 * @return number ticket
	 * @throws SQLException
	 */
	private int getNumberTicket() {
		try {
			connection = MySQLConnection.getConnection();
			statement = connection.createStatement();
			result = statement.executeQuery("SELECT idTicket FROM TICKET ORDER BY idTicket DESC LIMIT 1");
			if(result.first()) {
				return result.getInt("idTicket")+1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return 0;
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
			if(statement != null) {
				statement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
