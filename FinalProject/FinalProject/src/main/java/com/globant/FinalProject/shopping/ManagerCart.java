package com.globant.FinalProject.shopping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.globant.FinalProject.DAO.MySQLConnection;

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
	public boolean processBuy(ShoppingCart shoppingCart) throws SQLException {
		boolean bool = saveBuy(shoppingCart);
		if(!bool) {
			return false;
		}
		return shoppingCart.deleteAllProducts();
		
	}
	/**
	 * The method allows to insert ticket row into database. Several ticket row refer to  a given ticket.
	 * @param shoppingCart 
	 * @return result of the insert operation
	 * @throws SQLException
	 */
	private boolean saveBuy(ShoppingCart shoppingCart) throws SQLException {
		ArrayList<Product> products = shoppingCart.getProducts();
		if(!products.isEmpty()) {
			if(createTicket(shoppingCart.User().getUser())) {
				for(Product product: products) {
					Statement sd = connection.createStatement();
					ResultSet result = sd.executeQuery("SELECT value FROM PRODUCT WHERE idProduct =  \""+product.getIdProduct()+"\";");
					if(result.first()) {
						preparedStatement = connection.prepareStatement("INSERT INTO TICKET_ROW(idTicket, idUser, idProduct, quantity, unitValue) VALUES (?,?,?,?,?);");
						preparedStatement.setInt(1, ticketNumber);
						preparedStatement.setString(2, shoppingCart.User().getUser());
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
		return false;
	} 
	/**
	 * The method inserts the tickets into database. This ticket are saved in "Ticket" table
	 * @param idUser
	 * @return
	 * @throws SQLException
	 */
	private boolean createTicket(String idUser) throws SQLException {
		preparedStatement = connection.prepareStatement("INSERT INTO TICKET(idUser, dateBuy) VALUES (?,?);");
		preparedStatement.setString(1, idUser);
		preparedStatement.setDate(2, new Date(0));
		int i = preparedStatement.executeUpdate();
		if(i > 0) {
			return true;
		}
		return false;
	}
	/**
	 * This method execute a query to obtain last ticket number, because idTicket is auto increment.
	 * @return number ticket
	 * @throws SQLException
	 */
	private int getNumberTicket() throws SQLException {
		Statement sd = connection.createStatement();
		ResultSet result = sd.executeQuery("SELECT idTicket FROM TICKET ORDER BY idTicket DESC LIMIT 1");
		if(result.first()) {
			return result.getInt("idTicket")+1;
		}
		return 0;
	}
}
