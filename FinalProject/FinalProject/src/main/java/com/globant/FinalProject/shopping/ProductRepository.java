package com.globant.FinalProject.shopping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.globant.FinalProject.DB.MySQLConnection;

/**
 * This repository save into database all information of the products, allows to create, delete and update products.
 * @author her_1
 *
 */
public class ProductRepository {
	
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	
	public ProductRepository() throws SQLException {
		connection = MySQLConnection.getConnection();
	}
	/**
	 * The method allows to insert products into database
	 * @param product Product to insert
	 * @return 
	 * @throws SQLException
	 */
	public Product addProduct(Product product) throws SQLException {
		preparedStatement = connection.prepareStatement("INSERT INTO PRODUCT(idProduct, name, category, value) VALUES (?,?,?,?);");
		preparedStatement.setString(1, product.getIdProduct());
		preparedStatement.setString(2, product.getName());
		preparedStatement.setString(3, product.getCategory());
		preparedStatement.setFloat(4, product.getValue());
		int i = preparedStatement.executeUpdate();
		if(i > 0) {
			return product;
		}
		return null;
	}
	/**
	 * This method allows to find a product through its id.
	 * @param idProduct
	 * @return
	 * @throws SQLException
	 */
	public Product findProduct(String idProduct) throws SQLException {
		preparedStatement = connection.prepareStatement("SELECT * FROM PRODUCT WHERE idProduct = ?;");
		preparedStatement.setString(1, idProduct);
    	ResultSet result = preparedStatement.executeQuery();
    	Product product = null;
    	if(result.next()) {
    		product =  new Product(result.getString("idProduct"), result.getString("name"), result.getString("category"), result.getFloat("value"));
    	}
    	return product;
	}
	/**
	 * The method allows to update product. Executes the update query into database with new product information.
	 * @param product
	 * @return
	 * @throws SQLException
	 */
	public Product updateProduct(Product product) throws SQLException {
		preparedStatement = connection.prepareStatement("UPDATE PRODUCT SET name=?,category=?,value=? WHERE idProduct=?;");
		preparedStatement.setString(1, product.getName());
		preparedStatement.setString(2, product.getCategory());
		preparedStatement.setFloat(3, product.getValue());
		preparedStatement.setString(4, product.getIdProduct());
		int i = preparedStatement.executeUpdate();
		if(i > 0) {
			return product; 
		}
		return null;
	}
	/**
	 * the method handles the deletion into database. Is possible remove product through its id.
	 * @param idProduct
	 * @throws SQLException
	 */
	public void deleteProduct(String idProduct) throws SQLException {
		preparedStatement = connection.prepareStatement("DELETE FROM PRODUCT WHERE idProduct=?;");
		preparedStatement.setString(1, idProduct);
		preparedStatement.executeUpdate();
	}
	/**
	 * This method allows to find products by given category.
	 * @param category
	 * @return List of products
	 * @throws SQLException
	 */
	public ArrayList<Product> findByCategory(String category) throws SQLException {
		preparedStatement = connection.prepareStatement("SELECT * FROM PRODUCT WHERE category =?;");
		preparedStatement.setString(1, category);
    	ResultSet result = preparedStatement.executeQuery();
    	ArrayList<Product> products = new ArrayList<Product>();
    	while(result.next()) {
    		Product product = new Product(result.getString("idProduct"), result.getString("name"), result.getString("category"), result.getFloat("value"));
    		products.add(product);
    	}
    	return products;
	}
	/**
	 * The method return a list with all products. Executes a query that return all products in product Table
	 * @return 
	 * @throws SQLException
	 */
	public ArrayList<Product> getProducts() throws SQLException {
		preparedStatement = connection.prepareStatement("SELECT * FROM PRODUCT;");
    	ResultSet result = preparedStatement.executeQuery();
    	ArrayList<Product> products = new ArrayList<Product>();
    	while(result.next()) {
    		Product product = new Product(result.getString("idProduct"), result.getString("name"), result.getString("category"), result.getFloat("value"));
    		products.add(product);
    	}
    	return products;
	}
}
