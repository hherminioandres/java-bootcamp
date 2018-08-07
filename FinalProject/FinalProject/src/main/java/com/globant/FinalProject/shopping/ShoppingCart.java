package com.globant.FinalProject.shopping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.globant.FinalProject.DB.MySQLConnection;
import com.globant.FinalProject.user.User;

/**
 * This class defines behavior of shopping cart. Allows to add and remove products in the cart, get its total
 * value and obtain products quantity into the cart. All operations are made in databases.
 * The products are stored here while the buy is not paid. When the buy is paid, the products are
 * removed from this table and stored in the TICKET table
 * @author Herminio Andres Hernandez
 * @version 04/08/2018
 */
public class ShoppingCart {
	
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private User user;
	
	/**
	 * Constructor of shopping cart.
	 */
	public ShoppingCart(User user) throws SQLException {
		this.user = user;
		connection = MySQLConnection.getConnection();
	}
	/**
	 * The method executes a query that obtain the quantity of given product.  
	 * @param idProduct
	 * @return
	 * @throws SQLException
	 */
	public int getQuantityProduct(String idProduct) throws SQLException {
		int count = 0;
		preparedStatement = connection.prepareStatement("SELECT quantity FROM CART WHERE (idProduct=? AND idUser = ?)");
		preparedStatement.setString(1, idProduct);
		preparedStatement.setString(2, user.getUser());
    	ResultSet result = preparedStatement.executeQuery();
    	if(result.first()) {
    		count = result.getInt("quantity");
    	}
		return count;
	}
	/**
	 * The method allows to add products that insert into CART table. In this table just inserts idUser,
	 * idProduct and quantity of same product to buy. If exists idProduct to insert, just updates quantity.
	 * @param buy
	 * @return
	 * @throws SQLException
	 */
	public boolean addProduct(Buy buy) throws SQLException {
		int oldQuantity = getQuantityProduct(buy.getIdProduct());
		boolean result = false;
		if(oldQuantity > 0) {
			result = updateProduct(buy.getIdProduct(), buy.getQuantity()+oldQuantity);
		}else {
			preparedStatement = connection.prepareStatement("INSERT INTO CART(idUser, idProduct, quantity) VALUES (?,?,?);");
			preparedStatement.setString(1, user.getUser());
			preparedStatement.setString(2, buy.getIdProduct());
			preparedStatement.setInt(3, buy.getQuantity());
			int i = preparedStatement.executeUpdate();
			System.err.println("add: "+i);
			if(i > 0) {
				result = true;
			}
		}
		return result;
	}
	/**
	 * This method update the quantity field to given product and user.
	 * @param idProduct
	 * @param quantity
	 * @return
	 * @throws SQLException
	 */
	public boolean updateProduct(String idProduct, int quantity) throws SQLException {
		preparedStatement = connection.prepareStatement("UPDATE CART SET quantity=? WHERE (idProduct=? AND idUser=?);");
		preparedStatement.setInt(1, quantity);
		preparedStatement.setString(2, idProduct);
		preparedStatement.setString(3, user.getUser());
		int i = preparedStatement.executeUpdate();
		if(i > 0) {
			return true; 
		}
		return false;
	}
	/**
	 * This method allows to delete product of CART table. Just is necessary the idProduct.
	 * @param idProduct
	 * @throws SQLException
	 */
	public void deleteProduct(String idProduct) throws SQLException {
		preparedStatement = connection.prepareStatement("DELETE FROM CART WHERE (idProduct=? AND idUser=?);");
		preparedStatement.setString(1, idProduct);
		preparedStatement.setString(2, user.getUser());
		preparedStatement.executeUpdate();
	}
	/**
	 * This method remove all products to the CART table.
	 * @return
	 * @throws SQLException
	 */
	public boolean deleteAllProducts() throws SQLException {
		preparedStatement = connection.prepareStatement("DELETE FROM CART WHERE idUser=?;");
		preparedStatement.setString(1, user.getUser());
		int i = preparedStatement.executeUpdate();
		if(i > 0) {
			return true;
		}
		return false;
	}
	/**
	 * Searches products into PRODUCT table through the products ids that was save here to buy.
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Product> getProducts() throws SQLException {
		preparedStatement = connection.prepareStatement("SELECT p.*,c.quantity FROM PRODUCT p INNER JOIN CART c on (p.idProduct=c.idProduct) WHERE c.idUser = ?;");
		preparedStatement.setString(1, user.getUser());
    	ResultSet result = preparedStatement.executeQuery();
    	ArrayList<Product> products = new ArrayList<Product>();
    	while(result.next()) {
    		Product productAux = new Product(result.getString("idProduct"), result.getString("name"), result.getString("category"), result.getFloat("value"));
    		productAux.setQuantity(result.getInt("quantity"));
    		products.add(productAux);
    	}
    	return products;
	}
	/**
	 * This method calculate the buy's value.
	 * @return
	 * @throws SQLException
	 */
	public float calculate() throws SQLException {
		float sum = 0;
		preparedStatement = connection.prepareStatement("SELECT SUM(p.value*c.quantity) as \"sum\" FROM PRODUCT p INNER JOIN CART c on (p.idProduct=c.idProduct) WHERE c.idUser = ?;");
		preparedStatement.setString(1, user.getUser());
    	ResultSet result = preparedStatement.executeQuery();
    	if(result.first()) {
    		sum = result.getInt("sum");
    	}
		return sum;
	}
	/**
	 * The method return the products quantity into the cart. 
	 * @return
	 * @throws SQLException
	 */
	public int productQuantity() throws SQLException {
		int sum = 0;
		preparedStatement = connection.prepareStatement("SELECT COUNT(idUser) as \"count\" FROM CART WHERE idUser = ?;");
		preparedStatement.setString(1, user.getUser());
    	ResultSet result = preparedStatement.executeQuery();
    	if(result.first()) {
    		sum = result.getInt("count");
    	}
		return sum;
	}
	public User User() {
		return user;
	}
}