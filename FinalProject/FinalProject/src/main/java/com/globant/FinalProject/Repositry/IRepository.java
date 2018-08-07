package com.globant.FinalProject.Repositry;

import java.sql.SQLException;

/**
 * This interface defines the basic behavior of a repository
 * @author Herminio Andres Hernandez
 * @version 0708/2018
 * @param <T>
 */
public interface IRepository <T>{

	public T add(T element) throws SQLException;
	public T find(String element) throws SQLException;
	public T update(T element) throws SQLException;
	public void delete(String elements) throws SQLException;
}
