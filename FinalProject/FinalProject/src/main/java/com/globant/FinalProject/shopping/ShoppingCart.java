package com.globant.FinalProject.shopping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.globant.FinalProject.DAO.MySQLConnection;
import com.globant.FinalProject.DTO.DTOBuy;
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
	private ResultSet result = null;
	/**
	 * The method executes a query that obtain the quantity of given product.  
	 * @param idProduct
	 * @return
	 * @throws SQLException
	 */
	public int getQuantityProduct(User user, String idProduct) {
		int count = 0;
		try {
			connection = MySQLConnection.getConnection();
			preparedStatement = connection.prepareStatement("SELECT quantity FROM CART WHERE (idProduct=? AND idUser = ?)");
			preparedStatement.setString(1, idProduct);
			preparedStatement.setString(2, user.getUser());
			result = preparedStatement.executeQuery();
			if(result.first()) {
				count = result.getInt("quantity");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
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
	public boolean addProduct(User user, DTOBuy buy) {
		int oldQuantity = getQuantityProduct(user, buy.getIdProduct());
		boolean resultB = false;
		if(oldQuantity > 0) {
			resultB = updateProduct(user, buy.getIdProduct(), buy.getQuantity()+oldQuantity);
		}else {
			try {
				connection = MySQLConnection.getConnection();
				preparedStatement = connection.prepareStatement("INSERT INTO CART(idUser, idProduct, quantity) VALUES (?,?,?);");
				preparedStatement.setString(1, user.getUser());
				preparedStatement.setString(2, buy.getIdProduct());
				preparedStatement.setInt(3, buy.getQuantity());
				int i = preparedStatement.executeUpdate();
				if(i > 0) {
					resultB = true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.close();
			}
		}
		return resultB;
	}
	/**
	 * This method update the quantity field to given product and user.
	 * @param idProduct
	 * @param quantity
	 * @return
	 * @throws SQLException
	 */
	public boolean updateProduct(User user, String idProduct, int quantity) {
		try {
			connection = MySQLConnection.getConnection();
			preparedStatement = connection.prepareStatement("UPDATE CART SET quantity=? WHERE (idProduct=? AND idUser=?);");
			preparedStatement.setInt(1, quantity);
			preparedStatement.setString(2, idProduct);
			preparedStatement.setString(3, user.getUser());
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
	 * This method allows to delete product of CART table. Just is necessary the idProduct.
	 * @param idProduct
	 * @throws SQLException
	 */
	public void deleteProduct(User user, String idProduct) {
		try {
			connection = MySQLConnection.getConnection();
			preparedStatement = connection.prepareStatement("DELETE FROM CART WHERE (idProduct=? AND idUser=?);");
			preparedStatement.setString(1, idProduct);
			preparedStatement.setString(2, user.getUser());
			preparedStatement.executeUpdate();			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
	}
	/**
	 * This method remove all products to the CART table.
	 * @return
	 * @throws SQLException
	 */
	public boolean deleteAllProducts(User user) {
		try {
			connection = MySQLConnection.getConnection();
			preparedStatement = connection.prepareStatement("DELETE FROM CART WHERE idUser=?;");
			preparedStatement.setString(1, user.getUser());
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
	 * Searches products into PRODUCT table through the products ids that was save here to buy.
	 *  @return
	 *  @throws SQLException
	 **/
	public ArrayList<Product> getProducts(User user) {
		ArrayList<Product> products = new ArrayList<Product>();
		try {
			connection = MySQLConnection.getConnection();
			preparedStatement = connection.prepareStatement("SELECT p.*,c.quantity FROM PRODUCT p INNER JOIN CART c on (p.idProduct=c.idProduct) WHERE c.idUser = ?;");
			preparedStatement.setString(1, user.getUser());
			result = preparedStatement.executeQuery();
			while(result.next()) {
				Product productAux = new Product(result.getString("idProduct"), result.getString("name"), result.getString("category"), result.getFloat("value"));
				productAux.setQuantity(result.getInt("quantity"));
				products.add(productAux);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return products;
	}
	/**
	 * This method calculate the buy's value.
	 * @return
	 * @throws SQLException
	 */
	public float calculate(User user) {
		float sum = 0;
		try {
			connection = MySQLConnection.getConnection();
			preparedStatement = connection.prepareStatement("SELECT SUM(p.value*c.quantity) as \"sum\" FROM PRODUCT p INNER JOIN CART c on (p.idProduct=c.idProduct) WHERE c.idUser = ?;");
			preparedStatement.setString(1, user.getUser());
			result = preparedStatement.executeQuery();
			if(result.first()) {
				sum = result.getInt("sum");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return sum;
	}
	/**
	 * The method return the products quantity into the cart. 
	 * @return
	 * @throws SQLException
	 */
	public int productQuantity(User user) {
		int sum = 0;
		try {
			connection = MySQLConnection.getConnection();
			preparedStatement = connection.prepareStatement("SELECT COUNT(idUser) as \"count\" FROM CART WHERE idUser = ?;");
			preparedStatement.setString(1, user.getUser());
			result = preparedStatement.executeQuery();
			if(result.first()) {
				sum = result.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return sum;
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