package com.globant.FinalProject.shopping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.globant.FinalProject.DAO.MySQLConnection;
import com.globant.FinalProject.Repositry.IRepository;

/**
 * This repository save into database all information of the products, allows to create, delete and update products.
 * @author her_1
 *
 */
public class ProductRepository implements IRepository<Product> {
	
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet result = null;
	
	/**
	 * The method allows to insert products into database
	 * @param product Product to insert
	 * @return 
	 * @throws SQLException
	 */
	public Product add(Product product) {
		try {
			connection =  MySQLConnection.getConnection();
			preparedStatement = connection.prepareStatement("INSERT INTO PRODUCT(idProduct, name, category, value) VALUES (?,?,?,?);");
			preparedStatement.setString(1, product.getIdProduct());
			preparedStatement.setString(2, product.getName());
			preparedStatement.setString(3, product.getCategory());
			preparedStatement.setFloat(4, product.getValue());
			int i = preparedStatement.executeUpdate();
			if(i > 0) {
				return product;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return null;
	}
	/**
	 * This method allows to find a product through its id.
	 * @param idProduct
	 * @return
	 * @throws SQLException
	 */
	public Product find(String idProduct) {
		Product product = null;
		try {
			connection = MySQLConnection.getConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM PRODUCT WHERE idProduct = ?;");
			preparedStatement.setString(1, idProduct);
			result = preparedStatement.executeQuery();
			if(result.next()) {
				product =  new Product(result.getString("idProduct"), result.getString("name"), result.getString("category"), result.getFloat("value"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return product;
	}
	/**
	 * The method allows to update product. Executes the update query into database with new product information.
	 * @param product
	 * @return
	 * @throws SQLException
	 */
	public Product update(Product product) {
		try {
			connection = MySQLConnection.getConnection();
			preparedStatement = connection.prepareStatement("UPDATE PRODUCT SET name=?,category=?,value=? WHERE idProduct=?;");
			preparedStatement.setString(1, product.getName());
			preparedStatement.setString(2, product.getCategory());
			preparedStatement.setFloat(3, product.getValue());
			preparedStatement.setString(4, product.getIdProduct());
			int i = preparedStatement.executeUpdate();
			if(i > 0) {
				return product; 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return null;
	}
	/**
	 * the method handles the deletion into database. Is possible remove product through its id.
	 * @param idProduct
	 * @throws SQLException
	 */
	public void delete(String idProduct) {
		try {
			connection = MySQLConnection.getConnection();
			preparedStatement = connection.prepareStatement("DELETE FROM PRODUCT WHERE idProduct=?;");
			preparedStatement.setString(1, idProduct);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}

	}
	/**
	 * This method allows to find products by given category.
	 * @param category
	 * @return List of products
	 * @throws SQLException
	 */
	public List<Product> findByCategory(String category) {
		ArrayList<Product> products = new ArrayList<Product>();
		try {
			connection = MySQLConnection.getConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM PRODUCT WHERE category =?;");
			preparedStatement.setString(1, category);
			result = preparedStatement.executeQuery();
			while(result.next()) {
				Product product = new Product(result.getString("idProduct"), result.getString("name"), result.getString("category"), result.getFloat("value"));
				products.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return products;
	}
	/**
	 * The method return a list with all products. Executes a query that return all products in product Table
	 * @return 
	 * @throws SQLException
	 */
	public List<Product> getProducts() {
		ArrayList<Product> products = new ArrayList<Product>();
		try {
			connection = MySQLConnection.getConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM PRODUCT;");
			result = preparedStatement.executeQuery();
			while(result.next()) {
				Product product = new Product(result.getString("idProduct"), result.getString("name"), result.getString("category"), result.getFloat("value"));
				products.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return products;
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
